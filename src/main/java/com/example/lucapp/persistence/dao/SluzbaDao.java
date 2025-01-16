package com.example.lucapp.persistence.dao;

import com.example.lucapp.persistence.entity.Sluzba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SluzbaDao extends JpaRepository<Sluzba, Integer> {
}