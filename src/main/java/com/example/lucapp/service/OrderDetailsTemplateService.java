package com.example.lucapp.service;

import com.example.lucapp.persistence.dao.OrderDetailsTemplateDao;
import com.example.lucapp.dto.OrderDetailsTemplateDto;
import com.example.lucapp.persistence.entity.OrderDetailsTemplate;
import com.example.lucapp.dto.OrderDetailsTemplateWrapper;
import com.example.lucapp.exception.OrderNotFoundException;
import com.example.lucapp.exception.TemplateNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailsTemplateService {
    private final OrderDetailsTemplateDao orderDetailsTemplateDao;

    public List<OrderDetailsTemplateWrapper> getAllWrappedTemplates() {
        List<OrderDetailsTemplate> allTemplates = orderDetailsTemplateDao.findAll();
        return allTemplates.stream()
                .map(OrderDetailsTemplateWrapper::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderDetailsTemplateDto saveTemplate(OrderDetailsTemplateDto templateDto) {
        OrderDetailsTemplate template = new OrderDetailsTemplate(templateDto);
        OrderDetailsTemplate savedTemplate = orderDetailsTemplateDao.save(template);
        return new OrderDetailsTemplateDto(savedTemplate);
    }

    @Transactional
    public OrderDetailsTemplateDto updateTemplate(OrderDetailsTemplateDto templateDto, Integer id) {

        OrderDetailsTemplate existingTemplate = orderDetailsTemplateDao.findById(id).orElseThrow(
                () -> new TemplateNotFoundException("Cannot be updated, template with id " + id + " not found."));

        if(templateDto.getName() != null) {
            existingTemplate.setName(templateDto.getName());
        }

        if (templateDto.getOrderNumber() != null) {
            existingTemplate.setOrderNumber(templateDto.getOrderNumber());
        }

        if (templateDto.getDays() != null) {
            existingTemplate.setDays(templateDto.getDays());
        }

        if (templateDto.getKm() != null) {
            existingTemplate.setKm(templateDto.getKm());
        }

        if (templateDto.getHoursDay() != null) {
            existingTemplate.setHoursDay(templateDto.getHoursDay());
        }

        if (templateDto.getHoursNight() != null) {
            existingTemplate.setHoursNight(templateDto.getHoursNight());
        }

        if (templateDto.getMorningInstall() != null) {
            existingTemplate.setMorningInstall(templateDto.getMorningInstall());
        }

        if (templateDto.getNightDeinstall() != null) {
            existingTemplate.setNightDeinstall(templateDto.getNightDeinstall());
        }

        if (templateDto.getNights() != null) {
            existingTemplate.setNights(templateDto.getNights());
        }

        if (templateDto.getPricePerNight() != null) {
            existingTemplate.setPricePerNight(templateDto.getPricePerNight());
        }

        if (templateDto.getTheme() != null) {
            existingTemplate.setTheme(templateDto.getTheme());
        }

        if (templateDto.getCompany() != null) {
            existingTemplate.setCompany(templateDto.getCompany());
        }

        if (templateDto.getHtmlTemplate() != null) {
            existingTemplate.setHtmlTemplate(templateDto.getHtmlTemplate());
        }

        if (templateDto.getSetUpIds() != null) {
            existingTemplate.setSetUpIds(templateDto.getSetUpIds());
        }

        if (templateDto.getAccessoryIds() != null) {
            existingTemplate.setAccessoryIds(templateDto.getAccessoryIds());
        }

        if (templateDto.getDeliveryDiscountDisable() != null) {
            existingTemplate.setDeliveryDiscountDisable(templateDto.getDeliveryDiscountDisable());
        }

        OrderDetailsTemplate updatedOrder = orderDetailsTemplateDao.save(existingTemplate);

        return new OrderDetailsTemplateDto(updatedOrder);
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
        return new OrderDetailsTemplateDto(orderDetails);
    }

}
