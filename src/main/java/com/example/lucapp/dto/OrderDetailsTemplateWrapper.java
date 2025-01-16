package com.example.lucapp.dto;

import com.example.lucapp.persistence.entity.OrderDetailsTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetailsTemplateWrapper {

    private Integer id;
    private String name;

    public OrderDetailsTemplateWrapper(OrderDetailsTemplate  orderDetailsTemplate) {
        this.id = orderDetailsTemplate.getId();
        this.name = orderDetailsTemplate.getName();
    }
}
