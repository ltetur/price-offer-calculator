package com.ltetur.calculator.service;

import com.ltetur.calculator.dto.OrderDetailsDto;
import com.ltetur.calculator.exception.OrderNotFoundException;
import com.ltetur.calculator.persistence.dao.OrderDetailsDao;
import com.ltetur.calculator.persistence.entity.OrderDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing order details. Provides functionality
 * for creating, retrieving, updating, and deleting order details.
 */
@Service
@RequiredArgsConstructor
public class OrderDetailsService {

    private final OrderDetailsDao orderDetailsDao;

    /**
     * Saves a new order details based on the provided DTO.
     *
     * @param orderDetailsDto The DTO containing order details to be saved.
     * @return The saved order details as an {@link OrderDetailsDto}.
     */
    @Transactional
    public OrderDetailsDto saveOrderDetails(OrderDetailsDto orderDetailsDto) {
        OrderDetails orderDetails = new OrderDetails(orderDetailsDto);
        OrderDetails savedOrderDetails = this.orderDetailsDao.save(orderDetails);
        return new OrderDetailsDto(savedOrderDetails);
    }

    /**
     * Finds an order details entry by its ID.
     *
     * @param id The ID of the order details to find.
     * @return The found order details as an {@link OrderDetailsDto}.
     * @throws OrderNotFoundException if no order details are found with the given ID.
     */
    public OrderDetailsDto findById(Integer id) {
        OrderDetails orderDetails = orderDetailsDao.findById(id).orElseThrow(
                () -> new OrderNotFoundException("Order with id " + id + " not found.")
        );
        return new OrderDetailsDto(orderDetails);
    }

    /**
     * Updates an existing order details entry with the provided data.
     *
     * @param orderDetailsDto The DTO containing updated order details.
     * @param id              The ID of the order to update.
     * @return The updated order details as an {@link OrderDetailsDto}.
     * @throws OrderNotFoundException if no order details are found with the given ID.
     */
    @Transactional
    public OrderDetailsDto updateOrderDetails(OrderDetailsDto orderDetailsDto, Integer id) {
        OrderDetails existingOrder = orderDetailsDao.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Cannot be updated, order with id " + id + " not found."));

        if (orderDetailsDto.getName() != null) {
            existingOrder.setName(orderDetailsDto.getName());
        }

        if (orderDetailsDto.getOrderNumber() != null) {
            existingOrder.setOrderNumber(orderDetailsDto.getOrderNumber());
        }

        if (orderDetailsDto.getDays() != null) {
            existingOrder.setDays(orderDetailsDto.getDays());
        }

        if (orderDetailsDto.getKm() != null) {
            existingOrder.setKm(orderDetailsDto.getKm());
        }

        if (orderDetailsDto.getHoursDay() != null) {
            existingOrder.setHoursDay(orderDetailsDto.getHoursDay());
        }

        if (orderDetailsDto.getHoursNight() != null) {
            existingOrder.setHoursNight(orderDetailsDto.getHoursNight());
        }

        if (orderDetailsDto.getMorningInstall() != null) {
            existingOrder.setMorningInstall(orderDetailsDto.getMorningInstall());
        }

        if (orderDetailsDto.getNightDeinstall() != null) {
            existingOrder.setNightDeinstall(orderDetailsDto.getNightDeinstall());
        }

        if (orderDetailsDto.getNights() != null) {
            existingOrder.setNights(orderDetailsDto.getNights());
        }

        if (orderDetailsDto.getPricePerNight() != null) {
            existingOrder.setPricePerNight(orderDetailsDto.getPricePerNight());
        }

        if (orderDetailsDto.getHtmlTemplate() != null) {
            existingOrder.setHtmlTemplate(orderDetailsDto.getHtmlTemplate());
        }

        if (orderDetailsDto.getSetUpIds() != null) {
            existingOrder.setSetUpIds(orderDetailsDto.getSetUpIds());
        }

        if (orderDetailsDto.getAccessoryIds() != null) {
            existingOrder.setAccessoryIds(orderDetailsDto.getAccessoryIds());
        }

        if (orderDetailsDto.getDeliveryDiscountDisable() != null) {
            existingOrder.setDeliveryDiscountDisable(orderDetailsDto.getDeliveryDiscountDisable());
        }

        OrderDetails updatedOrder = orderDetailsDao.save(existingOrder);
        return new OrderDetailsDto(updatedOrder);
    }

    /**
     * Deletes an order details entry by its ID.
     *
     * @param id The ID of the order details to delete.
     * @throws OrderNotFoundException if no order details are found with the given ID.
     */
    @Transactional
    public void deleteOrderDetails(Integer id) {
        orderDetailsDao.findById(id).orElseThrow(
                () -> new OrderNotFoundException("Cannot be deleted, order with id " + id + " not found."));
        orderDetailsDao.deleteById(id);
    }

    /**
     * Retrieves all order details entries associated with a given order number,
     * sorted by update timestamp in descending order.
     *
     * @param orderNumber The order number to filter by.
     * @return A list of {@link OrderDetailsDto} matching the order number.
     */
    public List<OrderDetailsDto> getAllByOrderNumber(String orderNumber) {
        List<OrderDetails> orderDetails = orderDetailsDao.findByOrderNumberOrderByUpdatedAtDesc(orderNumber);
        return orderDetails.stream()
                .map(OrderDetailsDto::new)
                .collect(Collectors.toList());
    }
}
