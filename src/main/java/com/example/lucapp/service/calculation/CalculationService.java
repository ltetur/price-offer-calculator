package com.example.lucapp.service.calculation;

import com.example.lucapp.persistence.dao.AccessoryDao;
import com.example.lucapp.persistence.dao.DiscountDao;
import com.example.lucapp.persistence.dao.SetUpDao;
import com.example.lucapp.persistence.dao.SluzbaDao;
import com.example.lucapp.dto.OrderDetailsDto;
import com.example.lucapp.persistence.entity.Accessory;
import com.example.lucapp.persistence.entity.Item;
import com.example.lucapp.persistence.entity.SetUp;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalculationService {

    private final SluzbaDao sluzbaDao;
    private final DiscountDao discountDao;
    private final SetUpDao setUpDao;
    private final AccessoryDao accessoryDao;

    private static final int INSTALL_DAY_RATE_ID = 1;
    private static final int DEINSTALL_NIGHT_RATE_ID = 2;
    private static final int OPERATOR_DAY_RATE_ID = 3;
    private static final int OPERATOR_NIGHT_RATE_ID = 4;
    private static final int DELIVERY_PER_KM_ID = 5;
    private static final int DELIVERY_BASE_RATE_ID = 6;
    private static final int ACCOMMODATION_ID = 7;


    SetUp getSetupById(Integer id) {
        return setUpDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SetUp with id " + id + " not found"));
    }

    Accessory getAccessoryById(Integer id) {
        return accessoryDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Accessory with id " + id + " not found"));
    }

    Integer getSetUpCost(OrderDetailsDto orderDetailsDto, Integer setUpId) {
        double priceForDay = getSetupById(setUpId)
                .getItems()
                .stream()
                .mapToDouble(Item::getPriceCzk)
                .sum();

        int nextDays = orderDetailsDto.getDays() - 1;
        double totalPrice = priceForDay + (priceForDay * nextDays * 0.5);

        log.info("{}: {}", getSetupById(setUpId).getName(), (int) Math.round(totalPrice));
        return (int) Math.round(totalPrice);
    }

    private Integer getAccessoryCost(Integer accessoryId) {
        return getAccessoryById(accessoryId).getPriceCzk();
    }

    private Double getInstallTime(Integer setUpId) {
        return getSetupById(setUpId)
                .getItems()
                .stream()
                .mapToDouble(Item::getInstalInH)
                .sum();
    }

    private Double getDeinstallTime(Integer setUpId) {
        return getSetupById(setUpId)
                .getItems()
                .stream()
                .mapToDouble(Item::getDeinstalInH)
                .sum();
    }

    private Double calculateOperatorCost(OrderDetailsDto orderDetailsDto, List<Integer> setUpIds) {
        Double dayRate = sluzbaDao.findById(OPERATOR_DAY_RATE_ID).orElseThrow(() -> new EntityNotFoundException("OPERATOR_DAY_RATE_ID not found")).getPrice();
        Double nightRate = sluzbaDao.findById(OPERATOR_NIGHT_RATE_ID).orElseThrow(() -> new EntityNotFoundException("OPERATOR_NIGHT_RATE_ID not found")).getPrice();
        int operatorCount = setUpIds.size();

        return operatorCount * (orderDetailsDto.getHoursDay() * dayRate + orderDetailsDto.getHoursNight() * nightRate);
    }

    private Double calculateInstallAndDeinstallCost(OrderDetailsDto orderDetailsDto, List<Integer> setUpIds) {
        Double dayRate = sluzbaDao.findById(INSTALL_DAY_RATE_ID).orElseThrow(() -> new EntityNotFoundException("INSTALL_DAY_RATE_ID not found")).getPrice();
        Double nightRate = sluzbaDao.findById(DEINSTALL_NIGHT_RATE_ID).orElseThrow(() -> new EntityNotFoundException("DEINSTALL_NIGHT_RATE_ID not found")).getPrice();

        Double installRate = orderDetailsDto.getMorningInstall() ? nightRate : dayRate;
        Double deinstallRate = orderDetailsDto.getNightDeinstall() ? nightRate : dayRate;

        return setUpIds.stream()
                .mapToDouble(id -> getInstallTime(id) * installRate + getDeinstallTime(id) * deinstallRate)
                .sum();
    }

    private Double calculateAccommodationCost(OrderDetailsDto orderDetailsDto, List<Integer> setUpIds) {
        Double ratePerNight = orderDetailsDto.getPricePerNight() != null
                ? orderDetailsDto.getPricePerNight()
                : sluzbaDao.findById(ACCOMMODATION_ID).orElseThrow(() -> new EntityNotFoundException("ACCOMMODATION_ID not found")).getPrice();

        return orderDetailsDto.getNights() * ratePerNight * setUpIds.size();
    }

    private Double calculateServiceWithoutDelivery(OrderDetailsDto orderDetailsDto, List<Integer> setUpIds, List<Integer> accessoryIds) {
        return calculateOperatorCost(orderDetailsDto, setUpIds)
                + calculateInstallAndDeinstallCost(orderDetailsDto, setUpIds)
                + calculateAccommodationCost(orderDetailsDto, setUpIds)
                + setUpIds.stream().mapToDouble(iD -> getSetUpCost(orderDetailsDto, iD)).sum()
                + accessoryIds.stream().mapToDouble(this::getAccessoryCost).sum();
    }

    Double calculateDeliveryCost(OrderDetailsDto orderDetailsDto, List<Integer> setUpIds, List<Integer> accessoryIds) {
        Double deliveryRate = sluzbaDao.findById(DELIVERY_PER_KM_ID).orElseThrow(() -> new EntityNotFoundException("DELIVERY_PER_KM_ID not found")).getPrice();
        Double brnoDeliveryRate = sluzbaDao.findById(DELIVERY_BASE_RATE_ID).orElseThrow(() -> new EntityNotFoundException("DELIVERY_BASE_RATE_ID not found")).getPrice();
        Double deliveryPrice = brnoDeliveryRate + orderDetailsDto.getKm() * 2 * deliveryRate;

        Double priceWithoutDelivery = calculateServiceWithoutDelivery(orderDetailsDto, setUpIds, accessoryIds);

        return orderDetailsDto.getDeliveryDiscountDisable()
                ? deliveryPrice
                : deliveryPrice * discountDao.getDiscount(priceWithoutDelivery);
    }

    Integer calculateTotalServicePrice(OrderDetailsDto orderDetailsDto, List<Integer> setUpIds, List<Integer> accessoryIds) {
        Double operatorCost = calculateOperatorCost(orderDetailsDto, setUpIds);
        Double installAndDeinstallCost = calculateInstallAndDeinstallCost(orderDetailsDto, setUpIds);
        Double accommodationCost = calculateAccommodationCost(orderDetailsDto, setUpIds);
        Double deliveryCost = calculateDeliveryCost(orderDetailsDto, setUpIds, accessoryIds);

        log.info("operators+Cost : {}", operatorCost);
        log.info("install and deinstall : {}", installAndDeinstallCost);
        log.info("accommodation : {}", accommodationCost);
        log.info("delivery : {}", deliveryCost);

        return (int) Math.round(operatorCost + installAndDeinstallCost + accommodationCost + deliveryCost);
    }

    Integer calculateTotalPrice(OrderDetailsDto orderDetailsDto, List<Integer> setUpIds, List<Integer> accessoryIds) {
        double setUpPriceTotal = setUpIds.stream()
                .mapToDouble(iD -> getSetUpCost(orderDetailsDto, iD))
                .sum();

        double accessoriesCostTotal = accessoryIds.stream()
                .mapToDouble(this::getAccessoryCost)
                .sum();

        double totalServiceCost = calculateTotalServicePrice(orderDetailsDto, setUpIds, accessoryIds);

        return (int) Math.round(setUpPriceTotal + accessoriesCostTotal + totalServiceCost);
    }
}
