package com.ltetur.calculator.persistence.entity;


import com.ltetur.calculator.dto.OrderDetailsDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * The {@code OrderDetails} class represents the details of an order in the system.
 * It extends {@link OrderDetailsBase} and includes additional fields for tracking
 * creation and update timestamps.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class OrderDetails extends OrderDetailsBase {

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    /**
     * Constructs an OrderDetails entity from an OrderDetailsDto.
     *
     * @param orderDetailsDto the OrderDetailsDto to be converted
     */
    public OrderDetails(OrderDetailsDto orderDetailsDto) {
        super(orderDetailsDto.getId(), orderDetailsDto.getName(), orderDetailsDto.getOrderNumber(), orderDetailsDto.getDays(), orderDetailsDto.getKm(), orderDetailsDto.getHoursDay(), orderDetailsDto.getHoursNight(), orderDetailsDto.getMorningInstall(), orderDetailsDto.getNightDeinstall(), orderDetailsDto.getNights(), orderDetailsDto.getPricePerNight(), orderDetailsDto.getHtmlTemplate(), orderDetailsDto.getSetUpIds(), orderDetailsDto.getAccessoryIds(), orderDetailsDto.getDeliveryDiscountDisable());
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
