package com.example.lucapp.controller;

import com.example.lucapp.dto.OrderDetailsDto;
import com.example.lucapp.service.calculation.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("offer")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public ModelAndView calculateOfferAndFormatToHtml(@Valid @RequestBody OrderDetailsDto orderDetailsDto) {

        ModelAndView modelAndView = new ModelAndView(orderDetailsDto.getHtmlTemplate());

        modelAndView.addObject("title", orderDetailsDto.getName());
        modelAndView.addObject("company", orderDetailsDto.getCompany());
        modelAndView.addObject("offer", offerService.formatTextForHtml(offerService.getCompleteFormattedOffer(orderDetailsDto)));

        return modelAndView;
    }
}







