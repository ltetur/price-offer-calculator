package com.example.lucapp.controller;

import com.example.lucapp.dto.OrderDetailsDto;
import com.example.lucapp.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("offer")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping
    public ModelAndView calculateOffer(@Valid @RequestBody OrderDetailsDto orderDetailsDto) {

        ModelAndView modelAndView = new ModelAndView(orderDetailsDto.getHtmlTemplate());

        modelAndView.addObject("title", orderDetailsDto.getName());
        modelAndView.addObject("company", orderDetailsDto.getCompany());
        modelAndView.addObject("offer", offerService.formatTextForHtml(offerService.getCompleteFormattedOffer(orderDetailsDto)));

        return modelAndView;
    }
}







