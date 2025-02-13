package com.ltetur.calculator.persistence.dao;

import com.ltetur.calculator.persistence.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsDao extends JpaRepository<OrderDetails, Integer> {

    List<OrderDetails> findByOrderNumberOrderByUpdatedAtDesc(String orderNumber);

}
