package com.example.lucapp.dao;

import com.example.lucapp.entity.OrderDetailsTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsTemplateDao extends JpaRepository<OrderDetailsTemplate, Integer> {
}
