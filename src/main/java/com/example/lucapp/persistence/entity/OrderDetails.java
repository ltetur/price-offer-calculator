package com.example.lucapp.persistence.entity;


import com.example.lucapp.dto.OrderDetailsDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

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

    public OrderDetails(OrderDetailsDto orderDetailsDto) {
        super(orderDetailsDto.getId(), orderDetailsDto.getName(), orderDetailsDto.getOrderNumber(), orderDetailsDto.getDays(), orderDetailsDto.getKm(), orderDetailsDto.getHoursDay(), orderDetailsDto.getHoursNight(), orderDetailsDto.getMorningInstall(), orderDetailsDto.getNightDeinstall(), orderDetailsDto.getNights(), orderDetailsDto.getPricePerNight(), orderDetailsDto.getTheme(), orderDetailsDto.getCompany(), orderDetailsDto.getHtmlTemplate(), orderDetailsDto.getSetUpIds(), orderDetailsDto.getAccessoryIds(), orderDetailsDto.getDeliveryDiscountDisable());
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
