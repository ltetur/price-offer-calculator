package com.example.lucapp.service.calculation;

import com.example.lucapp.dto.OrderDetailsDto;
import com.example.lucapp.service.calculation.CalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormattingService {

    private final CalculationService calculationService;

    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(Locale.forLanguageTag("cs-CZ"));


    private String formatTotalServicePrice(OrderDetailsDto orderDetailsDto, List<Integer> setUpIds, List<Integer> accessoryIds) {
        String servicePrice = formatPriceNumber(calculationService.calculateTotalServicePrice(orderDetailsDto, setUpIds, accessoryIds));

        String serviceCostText;
        if (calculationService.calculateDeliveryCost(orderDetailsDto, setUpIds, accessoryIds) == 0) {
            serviceCostText = "Služby (instalace, obsluha): " + servicePrice + ", doprava zdarma";
        } else serviceCostText = "Služby (instalace, obsluha, doprava): " + servicePrice;

        return serviceCostText;
    }

    private String formatTotalPrice(OrderDetailsDto orderDetailsDto, List<Integer> setUpIds, List<Integer> accessoryIds) {
        String totalPrice = formatPriceNumber(calculationService.calculateTotalPrice(orderDetailsDto, setUpIds, accessoryIds));

        return "Celkem: " + totalPrice;
    }

    private List<String> getGroupedListOfSetupDescriptionsByIds(OrderDetailsDto orderDetailsDto, List<Integer> setUpIds) {
        Map<Integer, Long> IdGroupedByCount = setUpIds.stream()
                .collect(Collectors.groupingBy(num -> num, Collectors.counting()));

        return IdGroupedByCount.entrySet().stream()
                .map(entry -> {
                    Integer id = entry.getKey();
                    Long count = entry.getValue();
                    Long price = count * calculationService.getSetUpCost(orderDetailsDto, id);
                    return count + "x " + calculationService.getSetupById(id).getDescription() + " " + formatPriceNumber(price);
                }).collect(Collectors.toList());
    }

    private List<String> getListOfAccessoriesDescriptionByIds(List<Integer> accessoryIds) {
        return accessoryIds.stream()
                .map(calculationService::getAccessoryById)
                .map(a-> a.getName() + " " + formatPriceNumber(a.getPriceCzk()))
                .collect(Collectors.toList());
    }

    String formatCompleteOption(OrderDetailsDto orderDetailsDto, List<Integer> setUpIds, List<Integer> accessoryIds) {

        String names = setUpIds.stream().map(id -> calculationService.getSetupById(id).getCaption()).collect(Collectors.joining(", "));
        String descriptions = String.join(System.lineSeparator(), getGroupedListOfSetupDescriptionsByIds(orderDetailsDto, setUpIds));
        String accessories = String.join(System.lineSeparator(), getListOfAccessoriesDescriptionByIds(accessoryIds));
        String totalServicePrice = formatTotalServicePrice(orderDetailsDto, setUpIds, accessoryIds);
        String totalPrice = formatTotalPrice(orderDetailsDto, setUpIds, accessoryIds);

        return """
           %s
           %s
           %s%s
           %s
           """.formatted(
                names,
                descriptions,
                accessories.isEmpty() ? "" : accessories + System.lineSeparator(),
                totalServicePrice,
                totalPrice

        );
    }

    private String formatPriceNumber(Number price) {
        return NUMBER_FORMAT.format(price.intValue()) + " Kč";
    }


}
