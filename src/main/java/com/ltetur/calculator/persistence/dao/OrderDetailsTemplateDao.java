package com.ltetur.calculator.persistence.dao;

import com.ltetur.calculator.persistence.entity.OrderDetailsTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsTemplateDao extends JpaRepository<OrderDetailsTemplate, Integer> {
}
