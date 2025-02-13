package com.ltetur.calculator.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The {@code OrderDetailsBase} class serves as a base class for storing common order details.
 * The class is extended by {@link OrderDetails} and {@link OrderDetailsTemplate}
 */
@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
    @ElementCollection
    private List<List<Integer>> setUpIds;
    @ElementCollection
    private List<List<Integer>> accessoryIds;
    private Boolean deliveryDiscountDisable;
}
