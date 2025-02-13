package com.ltetur.calculator.dto;

import com.ltetur.calculator.persistence.entity.OrderDetails;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object (DTO) for order details.
 * This class is used to transfer order-related data between different layers of the application.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsDto {

    private Integer id;
    private String name;
    //private String currency;
    @Pattern(regexp = "\\d{4}", message = "The order number must be exactly 4 digits")
    private String orderNumber;
    @NotNull(message = "days value can not be null")
    @Positive(message = "days value must be positive")
    private Integer days;
    @NotNull(message = "km value can not be null")
    @Min(value = 0, message = "km value must be >= 0")
    private Integer km;
    @NotNull(message = "hoursDay value can not be null")
    @DecimalMin(value = "0.0")
    private Double hoursDay;
    @NotNull(message = "hoursNight value can not be null")
    @DecimalMin(value = "0.0")
    private Double hoursNight;
    @NotNull(message = "morningInstall value can not be null")
    private Boolean morningInstall;
    @NotNull(message = "nightInstall value can not be null")
    private Boolean nightDeinstall;
    private Integer nights;
    @Min(value = 0, message = "pricePerNight value must be >= 0")
    private Integer pricePerNight;
    @NotNull(message = "template value can not be null")
    private String htmlTemplate;
    @NotNull(message = "setUpIds value can not be null")
    private List<List<Integer>> setUpIds;
    @NotNull(message = "accessories value can not be null")
    private List<List<Integer>> accessoryIds;
    private Boolean deliveryDiscountDisable;


    /**
     * Constructs an OrderDetailsDto from an OrderDetails entity.
     *
     * @param orderDetails the OrderDetails entity to be converted
     */
    public OrderDetailsDto(OrderDetails orderDetails) {
        this.id = orderDetails.getId();
        this.name = orderDetails.getName();
        this.orderNumber = orderDetails.getOrderNumber();
        this.days = orderDetails.getDays();
        this.km = orderDetails.getKm();
        this.hoursDay = orderDetails.getHoursDay();
        this.hoursNight = orderDetails.getHoursNight();
        this.morningInstall = orderDetails.getMorningInstall();
        this.nightDeinstall = orderDetails.getNightDeinstall();
        this.nights = orderDetails.getNights();
        this.pricePerNight = orderDetails.getPricePerNight();
        this.htmlTemplate = orderDetails.getHtmlTemplate();
        this.setUpIds = orderDetails.getSetUpIds();
        this.accessoryIds = orderDetails.getAccessoryIds();
        this.deliveryDiscountDisable = orderDetails.getDeliveryDiscountDisable();
    }
}