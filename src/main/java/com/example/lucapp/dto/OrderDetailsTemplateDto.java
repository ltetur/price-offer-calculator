package com.ltetur.calculator.dto;

import com.ltetur.calculator.persistence.entity.OrderDetailsTemplate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object (DTO) for order details template.
 * This class is used to transfer template-related data between different layers of the application.
 */
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
    private String htmlTemplate;
    private List<List<Integer>> setUpIds;
    private List<List<Integer>> accessoryIds;
    private Boolean deliveryDiscountDisable;

    /**
     * Constructs an OrderDetailsTemplateDto from an OrderDetailsTemplate entity.
     *
     * @param orderDetailsTemplate the OrderDetailsTemplate entity to be converted
     */

    public OrderDetailsTemplateDto(OrderDetailsTemplate orderDetailsTemplate) {
        this.id = orderDetailsTemplate.getId();
        this.name = orderDetailsTemplate.getName();
        this.orderNumber = orderDetailsTemplate.getOrderNumber();
        this.days = orderDetailsTemplate.getDays();
        this.km = orderDetailsTemplate.getKm();
        this.hoursDay = orderDetailsTemplate.getHoursDay();
        this.hoursNight = orderDetailsTemplate.getHoursNight();
        this.morningInstall = orderDetailsTemplate.getMorningInstall();
        this.nightDeinstall = orderDetailsTemplate.getNightDeinstall();
        this.nights = orderDetailsTemplate.getNights();
        this.pricePerNight = orderDetailsTemplate.getPricePerNight();
        this.htmlTemplate = orderDetailsTemplate.getHtmlTemplate();
        this.setUpIds = orderDetailsTemplate.getSetUpIds();
        this.accessoryIds = orderDetailsTemplate.getAccessoryIds();
        this.deliveryDiscountDisable = orderDetailsTemplate.getDeliveryDiscountDisable();
    }
}

