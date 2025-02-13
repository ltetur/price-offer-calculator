package com.ltetur.calculator.service.calculation;

import com.ltetur.calculator.dto.OrderDetailsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for generating and formatting offers based on order details.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OfferService {

    private final FormattingService formattingService;

    /**
     * Generates a complete formatted offer based on the provided order details.
     * Each variant of the setup is labeled with a letter (A, B, C, etc.).
     *
     * @param orderDetailsDto The order details include information about the selected options.
     * @return A formatted offer as a string with variants separated by line breaks.
     */
    public String getCompleteFormattedOffer(OrderDetailsDto orderDetailsDto) {
        List<List<Integer>> ids = orderDetailsDto.getSetUpIds();
        List<List<Integer>> accessoryIds = orderDetailsDto.getAccessoryIds();

        StringBuilder offer = new StringBuilder();
        char iterator = 'A';

        for (int i = 0; i < ids.size(); i++) {
            log.info("Varianta: {}", iterator);
            String variant = "Varianta " + iterator + ": " +
                    formattingService.formatCompleteOption(orderDetailsDto, ids.get(i), accessoryIds.get(i));
            offer.append(variant).append(System.lineSeparator());
            iterator++;
        }

        return offer.toString();
    }

    /**
     * Formats a given text for HTML templates by replacing new line characters with <br> tags.
     *
     * @param text The input text to be formatted.
     * @return The formatted text with new lines converted to <br>, or null if the input is null.
     */
    public String formatTextForHtml(String text) {
        if (text == null) {
            return null;
        }
        return text.replaceAll("\\r?\\n", "<br>");
    }
}
