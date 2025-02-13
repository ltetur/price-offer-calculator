package com.ltetur.calculator.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * This class is a JPA entity and maps to a table that stores information about discounts.
 * A discount can be applied to the delivery price based on the total order amount.
 */
@Data
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double limitCzk;
    private Double coefficient;

}
