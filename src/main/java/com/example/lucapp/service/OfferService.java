package com.example.lucapp.service;

import com.example.lucapp.dto.OrderDetailsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OfferService {

    private final FormattingService formattingService;

    public OfferService(FormattingService formattingService) {
        this.formattingService = formattingService;
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());


    public String getCompleteFormattedOffer(OrderDetailsDto orderDetailsDto) {

        List<List<Integer>> ids = orderDetailsDto.getSetUpIds();
        List<List<Integer>> accessoryIds = orderDetailsDto.getAccessoryIds();

        StringBuilder offer = new StringBuilder();

        char iterator = 'A';

        for (int i = 0; i < ids.size(); i++) {
            logger.info("Varianta: {}", iterator);
            String variant = "Varianta " + iterator + ": " + formattingService.formatCompleteOption(orderDetailsDto, ids.get(i), accessoryIds.get(i));
            offer.append(variant).append(System.lineSeparator());
            iterator++;
        }

        return offer.toString();
    }

    public String formatTextForHtml(String text) {
        if (text == null) {
            return null;
        }
        return text.replaceAll("\\r?\\n", "<br>");
    }



}
