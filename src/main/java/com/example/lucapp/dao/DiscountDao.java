
package com.example.lucapp.dao;

import com.example.lucapp.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountDao extends JpaRepository<Discount, Integer> {

    @Query(value = "SELECT d.coefficient FROM discount d WHERE d.limit_czk <=:priceWithoutDelivery ORDER BY d.limit_czk DESC LIMIT 1", nativeQuery = true)
    Double getDiscount(@Param("priceWithoutDelivery") Double priceWithoutDelivery);
}




