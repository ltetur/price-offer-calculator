package com.ltetur.calculator.dto;

import com.ltetur.calculator.persistence.entity.OrderDetailsTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * A DTO wrapper for {@link OrderDetailsTemplate}, providing a simplified representation.
 * This class is used to transfer essential details of an order template.
 */
@Data
@AllArgsConstructor
public class OrderDetailsTemplateWrapper {

    private Integer id;
    private String name;

    /**
     * Constructs an {@code OrderDetailsTemplateWrapper} from an {@link OrderDetailsTemplate} entity.
     * Maps entity fields to DTO fields.
     *
     * @param orderDetailsTemplate the {@code OrderDetailsTemplate} entity to be converted
     */
    public OrderDetailsTemplateWrapper(OrderDetailsTemplate orderDetailsTemplate) {
        this.id = orderDetailsTemplate.getId();
        this.name = orderDetailsTemplate.getName();
    }
}
