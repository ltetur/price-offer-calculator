package com.ltetur.calculator.service;

import com.ltetur.calculator.dto.OrderDetailsTemplateDto;
import com.ltetur.calculator.dto.OrderDetailsTemplateWrapper;
import com.ltetur.calculator.exception.TemplateNotFoundException;
import com.ltetur.calculator.persistence.dao.OrderDetailsTemplateDao;
import com.ltetur.calculator.persistence.entity.OrderDetailsTemplate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing order details templates.
 * Templates are useful to store pre-filled data for standard orders that are often repeated.
 * Class provides functionality for retrieving, creating, updating, and deleting templates.
 */
@Service
@RequiredArgsConstructor
public class OrderDetailsTemplateService {

    private final OrderDetailsTemplateDao orderDetailsTemplateDao;

    /**
     * Retrieves all order details templates and wraps them into
     * {@link OrderDetailsTemplateWrapper} objects.
     *
     * @return A list of {@link OrderDetailsTemplateWrapper} containing all templates.
     */
    public List<OrderDetailsTemplateWrapper> getAllWrappedTemplates() {
        List<OrderDetailsTemplate> allTemplates = orderDetailsTemplateDao.findAll();
        return allTemplates.stream()
                .map(OrderDetailsTemplateWrapper::new)
                .collect(Collectors.toList());
    }

    /**
     * Saves a new order details template based on the provided data transfer object (DTO).
     *
     * @param templateDto The DTO containing the template information to be saved.
     * @return The saved template as an {@link OrderDetailsTemplateDto}.
     */
    @Transactional
    public OrderDetailsTemplateDto saveTemplate(OrderDetailsTemplateDto templateDto) {
        OrderDetailsTemplate template = new OrderDetailsTemplate(templateDto);
        OrderDetailsTemplate savedTemplate = orderDetailsTemplateDao.save(template);
        return new OrderDetailsTemplateDto(savedTemplate);
    }

    /**
     * Updates an existing order details template with the provided data from the DTO.
     *
     * @param templateDto The DTO containing the updated template information.
     * @param id          The ID of the template to be updated.
     * @return The updated template as an {@link OrderDetailsTemplateDto}.
     * @throws TemplateNotFoundException if the template with the specified ID is not found.
     */
    @Transactional
    public OrderDetailsTemplateDto updateTemplate(OrderDetailsTemplateDto templateDto, Integer id) {

        OrderDetailsTemplate existingTemplate = orderDetailsTemplateDao.findById(id).orElseThrow(
                () -> new TemplateNotFoundException("Cannot be updated, template with id " + id + " not found."));

        if (templateDto.getName() != null) {
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

    /**
     * Deletes the order details template with the specified ID.
     *
     * @param id The ID of the template to be deleted.
     * @throws TemplateNotFoundException if the template with the specified ID is not found.
     */
    @Transactional
    public void deleteTemplate(Integer id) {
        orderDetailsTemplateDao.findById(id).orElseThrow(
                () -> new TemplateNotFoundException("Cannot be deleted, template with id " + id + " not found."));
        orderDetailsTemplateDao.deleteById(id);
    }

    /**
     * Finds an order details template by its ID.
     *
     * @param id The ID of the template to find.
     * @return The found template as an {@link OrderDetailsTemplateDto}.
     * @throws TemplateNotFoundException if the template with the specified ID is not found.
     */
    public OrderDetailsTemplateDto findById(Integer id) {
        OrderDetailsTemplate orderDetails = orderDetailsTemplateDao.findById(id).orElseThrow(
                () -> new TemplateNotFoundException("Template with id " + id + " not found.")
        );
        return new OrderDetailsTemplateDto(orderDetails);
    }

}
