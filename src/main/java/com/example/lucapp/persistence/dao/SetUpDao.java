package com.example.lucapp.persistence.dao;

import com.example.lucapp.persistence.entity.SetUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetUpDao extends JpaRepository<SetUp, Integer> {
}
