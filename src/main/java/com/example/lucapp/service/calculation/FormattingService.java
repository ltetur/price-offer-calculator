package com.ltetur.calculator.service.calculation;

import com.ltetur.calculator.dto.OrderDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service class responsible for formatting calculated prices to text.
 */
@Service
@RequiredArgsConstructor
public class FormattingService {

    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(Locale.forLanguageTag("cs-CZ"));
    private final CalculationService calculationService;

    /**
     * Formats the total service price including installation, operation, and transportation costs.
     *
     * @param orderDetailsDto The order details.
     * @param setUpIds        List of setup IDs included in the order.
     * @param accessoryIds    List of accessory IDs included in the order.
     * @return Formatted string representing the total service cost.
     */
    private String formatTotalServicePrice(OrderDetailsDto orderDetailsDto, List<Integer> setUpIds, List<Integer> accessoryIds) {
        String servicePrice = formatPriceNumber(calculationService.calculateTotalServicePrice(orderDetailsDto, setUpIds, accessoryIds));

        if (calculationService.calculateDeliveryCost(orderDetailsDto, setUpIds, accessoryIds) == 0) {
            return "Služby (instalace, obsluha): " + servicePrice + ", doprava zdarma";
        }
        return "Služby (instalace, obsluha, doprava): " + servicePrice;
    }

    /**
     * Formats the total price of the order.
     *
     * @param orderDetailsDto The order details.
     * @param setUpIds        List of setup IDs included in the order.
     * @param accessoryIds    List of accessory IDs included in the order.
     * @return Formatted string representing the total order price.
     */
    private String formatTotalPrice(OrderDetailsDto orderDetailsDto, List<Integer> setUpIds, List<Integer> accessoryIds) {
        String totalPrice = formatPriceNumber(calculationService.calculateTotalPrice(orderDetailsDto, setUpIds, accessoryIds));
        return "Celkem: " + totalPrice;
    }

    /**
     * Retrieves and formats the grouped list of setup descriptions by their respective IDs.
     *
     * @param orderDetailsDto The order details.
     * @param setUpIds        List of setup IDs.
     * @return A list of formatted setup descriptions including quantity and price.
     */
    private List<String> getGroupedListOfSetupDescriptionsByIds(OrderDetailsDto orderDetailsDto, List<Integer> setUpIds) {
        Map<Integer, Long> idGroupedByCount = setUpIds.stream()
                .collect(Collectors.groupingBy(num -> num, Collectors.counting()));

        return idGroupedByCount.entrySet().stream()
                .map(entry -> {
                    Integer id = entry.getKey();
                    Long count = entry.getValue();
                    Long price = count * calculationService.getSetUpCost(orderDetailsDto, id);
                    return count + "x " + calculationService.getSetupById(id).getDescription() + " " + formatPriceNumber(price);
                }).toList();
    }

    /**
     * Retrieves and formats the list of accessory descriptions by their respective IDs.
     *
     * @param accessoryIds List of accessory IDs.
     * @return A list of formatted accessory descriptions including name and price.
     */
    private List<String> getListOfAccessoriesDescriptionByIds(List<Integer> accessoryIds) {
        return accessoryIds.stream()
                .map(calculationService::getAccessoryById)
                .map(a -> a.getName() + " " + formatPriceNumber(a.getPriceCzk()))
                .toList();
    }

    /**
     * Formats the complete description for an order option, including setup, accessories, and total pricing.
     *
     * @param orderDetailsDto The order details.
     * @param setUpIds        List of setup IDs.
     * @param accessoryIds    List of accessory IDs.
     * @return Formatted text representation of the complete order option.
     */
    String formatCompleteOption(OrderDetailsDto orderDetailsDto, List<Integer> setUpIds, List<Integer> accessoryIds) {
        String names = setUpIds.stream()
                .map(id -> calculationService.getSetupById(id).getCaption())
                .collect(Collectors.joining(", "));

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

    /**
     * Formats a price number according to Czech locale.
     *
     * @param price The price value.
     * @return Formatted price string in CZK currency.
     */
    private String formatPriceNumber(Number price) {
        return NUMBER_FORMAT.format(price.intValue()) + " Kč";
    }
}
