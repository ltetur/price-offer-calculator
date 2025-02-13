package com.ltetur.calculator.controller;

import com.ltetur.calculator.dto.OrderDetailsTemplateDto;
import com.ltetur.calculator.dto.OrderDetailsTemplateWrapper;
import com.ltetur.calculator.service.OrderDetailsTemplateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.equalToCompressingWhiteSpace;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderDetailsTemplateController.class)
public class OrderDetailsTemplateControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OrderDetailsTemplateService service;

    @Test
    public void TestGetAllTemplates_whenThereIsNoTemplate_returnEmptyList() throws Exception {

        when(service.getAllWrappedTemplates()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/templates"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", false));

    }

    @Test
    public void TestGetAllTemplates_whenTemplateIsPresent_ReturnTemplateWithStatusOk() throws Exception {

        OrderDetailsTemplateWrapper mockedWrapper = new OrderDetailsTemplateWrapper(
                1, // id
                "Sample Order" // name
        );

        when(service.getAllWrappedTemplates()).thenReturn(List.of(mockedWrapper));

        RequestBuilder request = get("/templates");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Sample Order\"}]", false));
    }

    @Test
    public void TestSaveTemplate_whenInputDataIsValid_returnTemplateWithStatusIsCreated() throws Exception {

        OrderDetailsTemplateDto mockDto = new OrderDetailsTemplateDto();

        mockDto.setId(1);
        mockDto.setName("sample template");

        String json = """ 
                {"id":1,"name":"sample template"}
                """;

        when(service.saveTemplate(mockDto)).thenReturn(mockDto);

        RequestBuilder request = post("/templates")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json(json, false))
                .andExpect(header().string("Location", "http://localhost/templates/1"));
    }

    @Test
    public void TestSaveTemplate_whenSavingWithEmptyName_returnBadRequest() throws Exception {

        String json = """ 
                {"name":null}
                """;

        RequestBuilder request = post("/templates")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON);


        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(equalToCompressingWhiteSpace("Validation errors: name can not be null;")));

    }
}

