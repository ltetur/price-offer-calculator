package com.example.lucapp.mapper.impl;

import com.example.lucapp.dto.OrderDetailsTemplateDto;
import com.example.lucapp.entity.OrderDetailsTemplate;
import com.example.lucapp.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailsTemplateMapperImpl implements Mapper<OrderDetailsTemplate, OrderDetailsTemplateDto> {

    private final ModelMapper modelMapper;

    public OrderDetailsTemplateMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public OrderDetailsTemplateDto mapTo(OrderDetailsTemplate orderDetailsTemplate) {
        return modelMapper.map(orderDetailsTemplate, OrderDetailsTemplateDto.class);
    }

    @Override
    public OrderDetailsTemplate mapFrom(OrderDetailsTemplateDto orderDetailsTemplateDto) {
        return modelMapper.map(orderDetailsTemplateDto, OrderDetailsTemplate.class);
    }
}
