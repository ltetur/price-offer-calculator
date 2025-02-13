package com.ltetur.calculator.persistence.dao;

import com.ltetur.calculator.persistence.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SluzbaDao extends JpaRepository<Service, Integer> {
}