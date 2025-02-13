package com.ltetur.calculator.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * This class is a JPA entity and maps to a table that stores information about items.
 * Items are basic components that can be rent with services,
 * these components are used to build setups packages {@link SetUp}.
 */
@Data
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double priceCzk;
    private Double instalInH;
    private Double deinstalInH;

}
