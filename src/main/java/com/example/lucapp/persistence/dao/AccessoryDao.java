package com.example.lucapp.persistence.dao;

import com.example.lucapp.persistence.entity.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessoryDao extends JpaRepository<Accessory, Integer> {
}
