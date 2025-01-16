package com.example.lucapp.persistence.entity;

import com.example.lucapp.dto.OrderDetailsTemplateDto;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NoArgsConstructor
public class OrderDetailsTemplate extends OrderDetailsBase {

    public OrderDetailsTemplate(OrderDetailsTemplateDto orderDetailsTemplateDto) {
        super(orderDetailsTemplateDto.getId(), orderDetailsTemplateDto.getName(), orderDetailsTemplateDto.getOrderNumber(), orderDetailsTemplateDto.getDays(), orderDetailsTemplateDto.getKm(), orderDetailsTemplateDto.getHoursDay(), orderDetailsTemplateDto.getHoursNight(), orderDetailsTemplateDto.getMorningInstall(), orderDetailsTemplateDto.getNightDeinstall(), orderDetailsTemplateDto.getNights(), orderDetailsTemplateDto.getPricePerNight(), orderDetailsTemplateDto.getTheme(), orderDetailsTemplateDto.getCompany(), orderDetailsTemplateDto.getHtmlTemplate(), orderDetailsTemplateDto.getSetUpIds(), orderDetailsTemplateDto.getAccessoryIds(), orderDetailsTemplateDto.getDeliveryDiscountDisable());
    }
}
