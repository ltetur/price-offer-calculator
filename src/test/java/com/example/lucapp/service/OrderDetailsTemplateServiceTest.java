package com.example.lucapp.service;


import com.example.lucapp.dto.OrderDetailsTemplateDto;
import com.example.lucapp.dto.OrderDetailsTemplateWrapper;
import com.example.lucapp.exception.TemplateNotFoundException;
import com.example.lucapp.persistence.dao.OrderDetailsTemplateDao;
import com.example.lucapp.persistence.entity.OrderDetailsTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderDetailsTemplateServiceTest {

    @Mock
    private OrderDetailsTemplateDao orderDetailsTemplateDao;

    @InjectMocks
    private OrderDetailsTemplateService orderDetailsTemplateService;

    private OrderDetailsTemplateDto orderDetailsTemplateDto;
    private OrderDetailsTemplate orderDetailsTemplate;


    @BeforeEach
    public void setup() {
        orderDetailsTemplateDto = new OrderDetailsTemplateDto();
        orderDetailsTemplateDto.setName("template1");

        orderDetailsTemplate = new OrderDetailsTemplate(orderDetailsTemplateDto);
    }

    @Test
    public void testSaveTemplate_success() {

        when(orderDetailsTemplateDao.save(any())).thenReturn(orderDetailsTemplate);

        OrderDetailsTemplateDto savedTemplate = orderDetailsTemplateService.saveTemplate(orderDetailsTemplateDto);

        assertThat(savedTemplate).isNotNull();

    }

    @Test
    public void testUpdateTemplate_templateFound() {

        when(orderDetailsTemplateDao.findById(1)).thenReturn(Optional.of(orderDetailsTemplate));
        when(orderDetailsTemplateDao.save(any(OrderDetailsTemplate.class))).thenReturn(orderDetailsTemplate);

        OrderDetailsTemplateDto updatedTemplate = new OrderDetailsTemplateDto();
        updatedTemplate.setName("template-updated");

        OrderDetailsTemplateDto updatedDto = orderDetailsTemplateService.updateTemplate(updatedTemplate, 1);

        assertThat(updatedDto.getName()).isEqualTo(("template-updated"));
    }

    @Test
    public void testUpdateTemplate_templateNotFound() {

        when(orderDetailsTemplateDao.findById(1)).thenReturn(Optional.empty());

        OrderDetailsTemplateDto updatedTemplate = new OrderDetailsTemplateDto();
        updatedTemplate.setName("template-updated");

        assertThrows(TemplateNotFoundException.class, () -> orderDetailsTemplateService.updateTemplate(updatedTemplate, 1));

        verify(orderDetailsTemplateDao, never()).save(any());

    }

    @Test
    public void testGetAllTemplates_templatesArePresent() {

        when(orderDetailsTemplateDao.findAll()).thenReturn(List.of(orderDetailsTemplate));

        List<OrderDetailsTemplateWrapper> wrappers = orderDetailsTemplateService.getAllWrappedTemplates();

        assertThat(wrappers).isNotNull();
        assertThat(wrappers).hasSize(1);

        assertThat(wrappers.get(0).getName()).isEqualTo(orderDetailsTemplate.getName());

    }


    @Test
    public void testGetAllTemplates_emptyList() {

        when(orderDetailsTemplateDao.findAll()).thenReturn(List.of());

        List<OrderDetailsTemplateWrapper> wrappers = orderDetailsTemplateService.getAllWrappedTemplates();

        assertThat(wrappers).isEmpty();
    }


    @Test
    public void testDeleteTemplateById_success() {

        when(orderDetailsTemplateDao.findById(1)).thenReturn(Optional.of(orderDetailsTemplate));

        doNothing().when(orderDetailsTemplateDao).deleteById(1);

        orderDetailsTemplateService.deleteTemplate(1);

        verify(orderDetailsTemplateDao, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteTemplateById_templateNotFound() {

        when(orderDetailsTemplateDao.findById(1)).thenReturn(Optional.empty());

        assertThrows(TemplateNotFoundException.class, () -> orderDetailsTemplateService.deleteTemplate(1));

        verify(orderDetailsTemplateDao, never()).deleteById(1);
    }

    @Test
    public void testFindById_templateFound() {

        when(orderDetailsTemplateDao.findById(1)).thenReturn(Optional.of(orderDetailsTemplate));

        OrderDetailsTemplateDto foundDto = orderDetailsTemplateService.findById(1);

        assertThat(foundDto).isNotNull();
        assertThat(foundDto.getName()).isEqualTo("template1");
    }

    @Test
    public void testFindById_templateNotFound() {

        when(orderDetailsTemplateDao.findById(1)).thenReturn(Optional.empty());

        assertThrows(TemplateNotFoundException.class, () -> orderDetailsTemplateService.findById(1));

        verify(orderDetailsTemplateDao, times(1)).findById(1);
    }

}
