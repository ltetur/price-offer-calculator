package com.example.lucapp.service;

import com.example.lucapp.persistence.dao.OrderDetailsTemplateDao;
import com.example.lucapp.dto.OrderDetailsTemplateDto;
import com.example.lucapp.persistence.entity.OrderDetailsTemplate;
import com.example.lucapp.dto.OrderDetailsTemplateWrapper;
import com.example.lucapp.exception.OrderNotFoundException;
import com.example.lucapp.exception.TemplateNotFoundException;
import com.example.lucapp.mapper.impl.OrderDetailsTemplateMapperImpl;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderDetailsTemplateService {
    private final OrderDetailsTemplateDao orderDetailsTemplateDao;

    private final OrderDetailsTemplateMapperImpl templateMapper;

    public OrderDetailsTemplateService(OrderDetailsTemplateDao orderDetailsTemplateDao, OrderDetailsTemplateMapperImpl templateMapper) {
        this.orderDetailsTemplateDao = orderDetailsTemplateDao;
        this.templateMapper = templateMapper;
    }

    public List<OrderDetailsTemplateWrapper> getAllWrappedTemplates() {
        List<OrderDetailsTemplate> allTemplates = orderDetailsTemplateDao.findAll();
        return allTemplates.stream()
                .map(template -> new OrderDetailsTemplateWrapper(template.getId(), template.getName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderDetailsTemplateDto saveTemplate(OrderDetailsTemplateDto templateDto) {
        OrderDetailsTemplate template = templateMapper.mapFrom(templateDto);
        OrderDetailsTemplate savedTemplate = orderDetailsTemplateDao.save(template);
        return templateMapper.mapTo(savedTemplate);
    }

    @Transactional
    public OrderDetailsTemplateDto updateTemplate(OrderDetailsTemplateDto templateDto, Integer id) {

        OrderDetailsTemplate existingTemplate = orderDetailsTemplateDao.findById(id).orElseThrow(
                () -> new TemplateNotFoundException("Cannot be updated, template with id " + id + " not found."));

        Optional.ofNullable(templateDto.getName()).ifPresent(existingTemplate::setName);
        Optional.ofNullable(templateDto.getOrderNumber()).ifPresent(existingTemplate::setOrderNumber);
        Optional.ofNullable(templateDto.getDays()).ifPresent(existingTemplate::setDays);
        Optional.ofNullable(templateDto.getKm()).ifPresent(existingTemplate::setKm);
        Optional.ofNullable(templateDto.getHoursDay()).ifPresent(existingTemplate::setHoursDay);
        Optional.ofNullable(templateDto.getHoursNight()).ifPresent(existingTemplate::setHoursNight);
        Optional.ofNullable(templateDto.getMorningInstall()).ifPresent(existingTemplate::setMorningInstall);
        Optional.ofNullable(templateDto.getNightDeinstall()).ifPresent(existingTemplate::setNightDeinstall);
        Optional.ofNullable(templateDto.getNights()).ifPresent(existingTemplate::setNights);
        Optional.ofNullable(templateDto.getPricePerNight()).ifPresent(existingTemplate::setPricePerNight);
        Optional.ofNullable(templateDto.getTheme()).ifPresent(existingTemplate::setTheme);
        Optional.ofNullable(templateDto.getCompany()).ifPresent(existingTemplate::setCompany);
        Optional.ofNullable(templateDto.getTemplate()).ifPresent(existingTemplate::setTemplate);
        Optional.ofNullable(templateDto.getSetUpIds()).ifPresent(existingTemplate::setSetUpIds);
        Optional.ofNullable(templateDto.getAccessories()).ifPresent(existingTemplate::setAccessories);
        Optional.ofNullable(templateDto.getDeliveryDiscountDisable()).ifPresent(existingTemplate::setDeliveryDiscountDisable);

        OrderDetailsTemplate updatedOrder = orderDetailsTemplateDao.save(existingTemplate);

        return templateMapper.mapTo(updatedOrder);
    }

    @Transactional
    public void deleteTemplate(Integer id) {
        orderDetailsTemplateDao.findById(id).orElseThrow(
                () -> new TemplateNotFoundException("Cannot be deleted, template with id " + id + " not found."));
        orderDetailsTemplateDao.deleteById(id);
    }

    public OrderDetailsTemplateDto findById(Integer id) {
        OrderDetailsTemplate orderDetails = orderDetailsTemplateDao.findById(id).orElseThrow(
                () -> new OrderNotFoundException("Template with id " + id + " not found.")
        );
        return templateMapper.mapTo(orderDetails);
    }

}
