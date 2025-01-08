package com.example.lucapp.controller;

import com.example.lucapp.dto.OrderDetailsTemplateDto;
import com.example.lucapp.dto.OrderDetailsTemplateWrapper;
import com.example.lucapp.service.OrderDetailsTemplateService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("templates")
public class OrderDetailsTemplateController {

    private final OrderDetailsTemplateService orderDetailsTemplateService;

    public OrderDetailsTemplateController(OrderDetailsTemplateService orderDetailsTemplateService) {
        this.orderDetailsTemplateService = orderDetailsTemplateService;

    }

    @GetMapping
    public ResponseEntity<List<OrderDetailsTemplateWrapper>> getAllTemplates() {
        return ResponseEntity.ok(orderDetailsTemplateService.getAllWrappedTemplates());
    }

    @PostMapping
    public ResponseEntity<OrderDetailsTemplateDto> saveTemplate(@Valid @RequestBody OrderDetailsTemplateDto templateDto) {
        OrderDetailsTemplateDto savedOrderDetailsTemplate = orderDetailsTemplateService.saveTemplate(templateDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOrderDetailsTemplate.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedOrderDetailsTemplate);
    }
    @PatchMapping("{id}")
    public ResponseEntity<OrderDetailsTemplateDto> updateTemplate(@Valid @RequestBody OrderDetailsTemplateDto templateDto, @PathVariable Integer id) {
        OrderDetailsTemplateDto savedTemplateDto = orderDetailsTemplateService.updateTemplate(templateDto, id);
        return ResponseEntity.ok(savedTemplateDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDetailsTemplateDto> getTemplateById(@PathVariable Integer id) {
        OrderDetailsTemplateDto templateDto = orderDetailsTemplateService.findById(id);
        return ResponseEntity.ok(templateDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable Integer id) {
        orderDetailsTemplateService.deleteTemplate(id);
        return ResponseEntity.noContent().build();
    }
}
