package com.example.lucapp.service;

import com.example.lucapp.persistence.dao.OrderDetailsDao;
import com.example.lucapp.dto.OrderDetailsDto;
import com.example.lucapp.persistence.entity.OrderDetails;
import com.example.lucapp.exception.OrderNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailsService {
    private final OrderDetailsDao orderDetailsDao;

    @Transactional
    public OrderDetailsDto saveOrderDetails(OrderDetailsDto orderDetailsDto) {
        OrderDetails orderDetails = new OrderDetails(orderDetailsDto);
        OrderDetails savedOrderDetails = this.orderDetailsDao.save(orderDetails);
        return new OrderDetailsDto(savedOrderDetails);
    }

    public OrderDetailsDto findById(Integer id) {
        OrderDetails orderDetails = orderDetailsDao.findById(id).orElseThrow(
                () -> new OrderNotFoundException("Order with id " + id + " not found.")
        );
        return new OrderDetailsDto(orderDetails);
    }
    @Transactional
    public OrderDetailsDto updateOrderDetails(OrderDetailsDto orderDetailsDto, Integer id) {
        OrderDetails existingOrder = orderDetailsDao.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Cannot be updated, order with id " + id + " not found."));

        if (orderDetailsDto.getName() != null) {
            existingOrder.setName(orderDetailsDto.getName());
        }

        if (orderDetailsDto.getOrderNumber() != null) {
            existingOrder.setOrderNumber(orderDetailsDto.getOrderNumber());
        }

        if (orderDetailsDto.getDays() != null) {
            existingOrder.setDays(orderDetailsDto.getDays());
        }

        if (orderDetailsDto.getKm() != null) {
            existingOrder.setKm(orderDetailsDto.getKm());
        }

        if (orderDetailsDto.getHoursDay() != null) {
            existingOrder.setHoursDay(orderDetailsDto.getHoursDay());
        }

        if (orderDetailsDto.getHoursNight() != null) {
            existingOrder.setHoursNight(orderDetailsDto.getHoursNight());
        }

        if (orderDetailsDto.getMorningInstall() != null) {
            existingOrder.setMorningInstall(orderDetailsDto.getMorningInstall());
        }

        if (orderDetailsDto.getNightDeinstall() != null) {
            existingOrder.setNightDeinstall(orderDetailsDto.getNightDeinstall());
        }

        if (orderDetailsDto.getNights() != null) {
            existingOrder.setNights(orderDetailsDto.getNights());
        }

        if (orderDetailsDto.getPricePerNight() != null) {
            existingOrder.setPricePerNight(orderDetailsDto.getPricePerNight());
        }

        if (orderDetailsDto.getTheme() != null) {
            existingOrder.setTheme(orderDetailsDto.getTheme());
        }

        if (orderDetailsDto.getCompany() != null) {
            existingOrder.setCompany(orderDetailsDto.getCompany());
        }

        if (orderDetailsDto.getHtmlTemplate() != null) {
            existingOrder.setHtmlTemplate(orderDetailsDto.getHtmlTemplate());
        }

        if (orderDetailsDto.getSetUpIds() != null) {
            existingOrder.setSetUpIds(orderDetailsDto.getSetUpIds());
        }

        if (orderDetailsDto.getAccessoryIds() != null) {
            existingOrder.setAccessoryIds(orderDetailsDto.getAccessoryIds());
        }

        if (orderDetailsDto.getDeliveryDiscountDisable() != null) {
            existingOrder.setDeliveryDiscountDisable(orderDetailsDto.getDeliveryDiscountDisable());
        }


        OrderDetails updatedOrder = orderDetailsDao.save(existingOrder);

        return new OrderDetailsDto(updatedOrder);
    }

    @Transactional
    public void deleteOrderDetails(Integer id) {
        orderDetailsDao.findById(id).orElseThrow(
                () -> new OrderNotFoundException("Cannot be deleted, order with id " + id + " not found."));
        orderDetailsDao.deleteById(id);
    }

    public List<OrderDetailsDto> getAllByOrderNumber(String orderNumber) {
        List<OrderDetails> orderDetails = orderDetailsDao.findByOrderNumberOrderByUpdatedAtDesc(orderNumber);
        return orderDetails.stream()
                .map(OrderDetailsDto::new)
                .collect(Collectors.toList());
    }
}