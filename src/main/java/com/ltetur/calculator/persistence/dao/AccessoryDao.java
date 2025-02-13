package com.ltetur.calculator.persistence.dao;

import com.ltetur.calculator.persistence.entity.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessoryDao extends JpaRepository<Accessory, Integer> {
}
