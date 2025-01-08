package com.example.lucapp.dao;

import com.example.lucapp.entity.SetUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetUpDao extends JpaRepository<SetUp, Integer> {
}
