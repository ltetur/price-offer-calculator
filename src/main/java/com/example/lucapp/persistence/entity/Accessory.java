package com.ltetur.calculator.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * This class is a JPA entity and maps to a table that stores information about accessories.
 * Accessories are various items and services that can be added extra to price offer.
 */
@Data
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Accessory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer priceCzk;
}
