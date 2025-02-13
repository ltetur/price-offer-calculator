package com.ltetur.calculator.persistence.entity;

import com.ltetur.calculator.dto.OrderDetailsTemplateDto;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The {@code OrderDetails} class represents the order detail templates in the system.
 * Templates are used to store predefined order details data. They are used to save time on repetitive orders.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NoArgsConstructor
public class OrderDetailsTemplate extends OrderDetailsBase {
    /**
     * Constructs an OrderDetailsTemplate entity from an OrderDetailsTemplateDto.
     *
     * @param orderDetailsTemplateDto the OrderDetailsTemplateDto to be converted
     */
    public OrderDetailsTemplate(OrderDetailsTemplateDto orderDetailsTemplateDto) {
        super(orderDetailsTemplateDto.getId(), orderDetailsTemplateDto.getName(), orderDetailsTemplateDto.getOrderNumber(), orderDetailsTemplateDto.getDays(), orderDetailsTemplateDto.getKm(), orderDetailsTemplateDto.getHoursDay(), orderDetailsTemplateDto.getHoursNight(), orderDetailsTemplateDto.getMorningInstall(), orderDetailsTemplateDto.getNightDeinstall(), orderDetailsTemplateDto.getNights(), orderDetailsTemplateDto.getPricePerNight(), orderDetailsTemplateDto.getHtmlTemplate(), orderDetailsTemplateDto.getSetUpIds(), orderDetailsTemplateDto.getAccessoryIds(), orderDetailsTemplateDto.getDeliveryDiscountDisable());
    }
}
