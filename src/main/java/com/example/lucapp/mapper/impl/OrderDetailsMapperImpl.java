package com.example.lucapp.mapper.impl;

import com.example.lucapp.dto.OrderDetailsDto;
import com.example.lucapp.entity.OrderDetails;
import com.example.lucapp.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailsMapperImpl implements Mapper<OrderDetails, OrderDetailsDto> {

    private final ModelMapper modelMapper;

    public OrderDetailsMapperImpl (ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public OrderDetailsDto mapTo(OrderDetails orderDetails) {
        return modelMapper.map(orderDetails, OrderDetailsDto.class);
    }

    @Override
    public OrderDetails mapFrom(OrderDetailsDto orderDetailsDto) {
        return modelMapper.map(orderDetailsDto, OrderDetails.class);
    }
}
