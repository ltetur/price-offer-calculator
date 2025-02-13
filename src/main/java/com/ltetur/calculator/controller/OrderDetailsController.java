package com.ltetur.calculator.controller;

import com.ltetur.calculator.dto.OrderDetailsDto;
import com.ltetur.calculator.service.OrderDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * REST controller for managing order details.
 * Provides endpoints to create, update, retrieve, and delete orders.
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    /**
     * Saves a new order to the database.
     *
     * @param orderDetailsDto the details of the order to be saved
     * @return a {@link ResponseEntity} containing the created order and its location URI
     */
    @PostMapping
    public ResponseEntity<OrderDetailsDto> saveOrderDetails(@Valid @RequestBody OrderDetailsDto orderDetailsDto) {
        OrderDetailsDto savedOrderDetailsDto = orderDetailsService.saveOrderDetails(orderDetailsDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOrderDetailsDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedOrderDetailsDto);
    }

    /**
     * Updates an existing order with new details.
     *
     * @param orderDetailsDto the updated order details
     * @param id              the ID of the order to be updated
     * @return a {@link ResponseEntity} containing the updated order
     */
    @PatchMapping("{id}")
    public ResponseEntity<OrderDetailsDto> updateOrderDetails(@Valid @RequestBody OrderDetailsDto orderDetailsDto, @PathVariable Integer id) {
        OrderDetailsDto savedOrderDetailsDto = orderDetailsService.updateOrderDetails(orderDetailsDto, id);
        return ResponseEntity.ok(savedOrderDetailsDto);
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id the ID of the order to retrieve
     * @return a {@link ResponseEntity} containing the requested order
     */
    @GetMapping("{id}")
    public ResponseEntity<OrderDetailsDto> getOrderDetailsById(@PathVariable Integer id) {
        OrderDetailsDto orderDetailsDto = orderDetailsService.findById(id);
        return ResponseEntity.ok(orderDetailsDto);
    }

    /**
     * Retrieves all orders associated with a specific order number.
     *
     * @param orderNumber the order number to filter by
     * @return a {@link ResponseEntity} containing a list of matching orders
     */
    @GetMapping
    public ResponseEntity<List<OrderDetailsDto>> getAllByOrderNumber(@RequestParam String orderNumber) {
        return ResponseEntity.ok(orderDetailsService.getAllByOrderNumber(orderNumber));
    }

    /**
     * Deletes an order by its ID.
     *
     * @param id the ID of the order to delete
     * @return a {@link ResponseEntity} with no content upon successful deletion
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOrderDetails(@PathVariable Integer id) {
        orderDetailsService.deleteOrderDetails(id);
        return ResponseEntity.noContent().build();
    }
}
