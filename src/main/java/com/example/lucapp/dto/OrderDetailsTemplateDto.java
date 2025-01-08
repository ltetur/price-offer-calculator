package com.example.lucapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsTemplateDto {

    private Integer id;
    @NotNull(message = "name can not be null")
    private String name;
    //private String currency;
    private String orderNumber;
    private Integer days;
    private Integer km;
    private Double hoursDay;
    private Double hoursNight;
    private Boolean morningInstall;
    private Boolean nightDeinstall;
    private Integer nights;
    private Integer pricePerNight;
    private String theme;
    private String company;
    private String template;
    private List<List<Integer>> setUpIds;
    private List<List<Integer>> accessories;
    private Boolean deliveryDiscountDisable;
}

