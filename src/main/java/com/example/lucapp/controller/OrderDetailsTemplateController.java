package com.ltetur.calculator.controller;

import com.ltetur.calculator.dto.OrderDetailsTemplateDto;
import com.ltetur.calculator.dto.OrderDetailsTemplateWrapper;
import com.ltetur.calculator.service.OrderDetailsTemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * REST controller for managing order detail templates.
 * Provides endpoints to create, update, retrieve, and delete templates.
 */
@RestController
@RequestMapping("templates")
@RequiredArgsConstructor
public class OrderDetailsTemplateController {

    private final OrderDetailsTemplateService orderDetailsTemplateService;

    /**
     * Retrieves all available order detail templates.
     *
     * @return a {@link ResponseEntity} containing a list of wrapped order detail templates
     */
    @GetMapping
    public ResponseEntity<List<OrderDetailsTemplateWrapper>> getAllTemplates() {
        return ResponseEntity.ok(orderDetailsTemplateService.getAllWrappedTemplates());
    }

    /**
     * Saves a new order detail template to the database.
     *
     * @param templateDto the details of the template to be saved
     * @return a {@link ResponseEntity} containing the created template and its location URI
     */
    @PostMapping
    public ResponseEntity<OrderDetailsTemplateDto> saveTemplate(@Valid @RequestBody OrderDetailsTemplateDto templateDto) {
        OrderDetailsTemplateDto savedOrderDetailsTemplate = orderDetailsTemplateService.saveTemplate(templateDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOrderDetailsTemplate.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedOrderDetailsTemplate);
    }

    /**
     * Updates an existing order detail template.
     *
     * @param templateDto the updated template details
     * @param id          the ID of the template to be updated
     * @return a {@link ResponseEntity} containing the updated template
     */
    @PatchMapping("{id}")
    public ResponseEntity<OrderDetailsTemplateDto> updateTemplate(@Valid @RequestBody OrderDetailsTemplateDto templateDto, @PathVariable Integer id) {
        OrderDetailsTemplateDto savedTemplateDto = orderDetailsTemplateService.updateTemplate(templateDto, id);
        return ResponseEntity.ok(savedTemplateDto);
    }

    /**
     * Retrieves an order detail template by its ID.
     *
     * @param id the ID of the template to retrieve
     * @return a {@link ResponseEntity} containing the requested template
     */
    @GetMapping("{id}")
    public ResponseEntity<OrderDetailsTemplateDto> getTemplateById(@PathVariable Integer id) {
        OrderDetailsTemplateDto templateDto = orderDetailsTemplateService.findById(id);
        return ResponseEntity.ok(templateDto);
    }

    /**
     * Deletes an order detail template by its ID.
     *
     * @param id the ID of the template to delete
     * @return a {@link ResponseEntity} with no content upon successful deletion
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable Integer id) {
        orderDetailsTemplateService.deleteTemplate(id);
        return ResponseEntity.noContent().build();
    }
}
