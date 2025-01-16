package com.example.lucapp.persistence.dao;

import com.example.lucapp.persistence.entity.OrderDetailsTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsTemplateDao extends JpaRepository<OrderDetailsTemplate, Integer> {
}
