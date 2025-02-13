package com.ltetur.calculator.persistence.dao;

import com.ltetur.calculator.persistence.entity.SetUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetUpDao extends JpaRepository<SetUp, Integer> {
}
