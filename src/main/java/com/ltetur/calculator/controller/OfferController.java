package com.ltetur.calculator.controller;

import com.ltetur.calculator.dto.OrderDetailsDto;
import com.ltetur.calculator.service.calculation.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller responsible for handling price offer calculations.
 */
@Controller
@RequestMapping("offer")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    /**
     * Processes a price offer request, performs the calculations, formats the text,
     * and returns an HTML page with the formatted offer.
     *
     * @param orderDetailsDto the details of the order, including input parameters for the price calculation
     * @return a {@link ModelAndView} containing the HTML template and formatted offer
     */
    @PostMapping
    public ModelAndView calculateOfferAndFormatToHtml(@Valid @RequestBody OrderDetailsDto orderDetailsDto) {
        ModelAndView modelAndView = new ModelAndView(orderDetailsDto.getHtmlTemplate());

        modelAndView.addObject("title", orderDetailsDto.getName());
        modelAndView.addObject("offer", offerService.formatTextForHtml(offerService.getCompleteFormattedOffer(orderDetailsDto)));

        return modelAndView;
    }
}
