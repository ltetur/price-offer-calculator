package com.example.lucapp.service;

import com.example.lucapp.dao.OrderDetailsDao;
import com.example.lucapp.dto.OrderDetailsDto;
import com.example.lucapp.entity.OrderDetails;
import com.example.lucapp.exception.OrderNotFoundException;
import com.example.lucapp.mapper.impl.OrderDetailsMapperImpl;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderDetailsService {
    private final OrderDetailsDao orderDetailsDao;
    private final OrderDetailsMapperImpl orderDetailsMapper;

    public OrderDetailsService(OrderDetailsDao orderDetailsDao, OrderDetailsMapperImpl orderDetailsMapper) {
        this.orderDetailsDao = orderDetailsDao;
        this.orderDetailsMapper = orderDetailsMapper;
    }

    @Transactional
    public OrderDetailsDto saveOrderDetails(OrderDetailsDto orderDetailsDto) {
        OrderDetails orderDetails = orderDetailsMapper.mapFrom(orderDetailsDto);
        OrderDetails savedOrderDetails = this.orderDetailsDao.save(orderDetails);
        return orderDetailsMapper.mapTo(savedOrderDetails);
    }

    public OrderDetailsDto findById(Integer id) {
        OrderDetails orderDetails = orderDetailsDao.findById(id).orElseThrow(
                () -> new OrderNotFoundException("Order with id " + id + " not found.")
        );
        return orderDetailsMapper.mapTo(orderDetails);
    }
    @Transactional
    public OrderDetailsDto updateOrderDetails(OrderDetailsDto orderDetailsDto, Integer id) {
        OrderDetails existingOrder = orderDetailsDao.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Cannot be updated, order with id " + id + " not found."));

        Optional.ofNullable(orderDetailsDto.getName()).ifPresent(existingOrder::setName);
        Optional.ofNullable(orderDetailsDto.getOrderNumber()).ifPresent(existingOrder::setOrderNumber);
        Optional.ofNullable(orderDetailsDto.getDays()).ifPresent(existingOrder::setDays);
        Optional.ofNullable(orderDetailsDto.getKm()).ifPresent(existingOrder::setKm);
        Optional.ofNullable(orderDetailsDto.getHoursDay()).ifPresent(existingOrder::setHoursDay);
        Optional.ofNullable(orderDetailsDto.getHoursNight()).ifPresent(existingOrder::setHoursNight);
        Optional.ofNullable(orderDetailsDto.getMorningInstall()).ifPresent(existingOrder::setMorningInstall);
        Optional.ofNullable(orderDetailsDto.getNightDeinstall()).ifPresent(existingOrder::setNightDeinstall);
        Optional.ofNullable(orderDetailsDto.getNights()).ifPresent(existingOrder::setNights);
        Optional.ofNullable(orderDetailsDto.getPricePerNight()).ifPresent(existingOrder::setPricePerNight);
        Optional.ofNullable(orderDetailsDto.getTheme()).ifPresent(existingOrder::setTheme);
        Optional.ofNullable(orderDetailsDto.getCompany()).ifPresent(existingOrder::setCompany);
        Optional.ofNullable(orderDetailsDto.getHtmlTemplate()).ifPresent(existingOrder::setTemplate);
        Optional.ofNullable(orderDetailsDto.getSetUpIds()).ifPresent(existingOrder::setSetUpIds);
        Optional.ofNullable(orderDetailsDto.getAccessoryIds()).ifPresent(existingOrder::setAccessories);
        Optional.ofNullable(orderDetailsDto.getDeliveryDiscountDisable()).ifPresent(existingOrder::setDeliveryDiscountDisable);

        OrderDetails updatedOrder = orderDetailsDao.save(existingOrder);

        return orderDetailsMapper.mapTo(updatedOrder);
    }

    @Transactional
    public void deleteOrderDetails(Integer id) {
        orderDetailsDao.findById(id).orElseThrow(
                () -> new OrderNotFoundException("Cannot be deleted, order with id " + id + " not found."));
        orderDetailsDao.deleteById(id);
    }

    public List<OrderDetailsDto> getAllByOrderNumber(String orderNumber) {
        List<OrderDetails> orderDetails = orderDetailsDao.findByOrderNumberOrderByUpdatedAtDesc(orderNumber);
        return orderDetails.stream()
                .map(orderDetailsMapper::mapTo)
                .collect(Collectors.toList());
    }
}