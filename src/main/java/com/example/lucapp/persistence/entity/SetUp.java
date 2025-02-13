package com.ltetur.calculator.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a JPA entity and maps to a table that stores information about setups.
 * Setup is a collection of items {@link Item} that can be rented together.
 */
@Data
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SetUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToMany
    private List<Item> items = new ArrayList<>();
    @Column(columnDefinition = "TEXT")
    private String caption;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String category;
}
