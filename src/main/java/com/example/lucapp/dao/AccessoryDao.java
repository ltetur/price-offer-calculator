package com.example.lucapp.dao;

import com.example.lucapp.entity.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessoryDao extends JpaRepository<Accessory, Integer> {
}
