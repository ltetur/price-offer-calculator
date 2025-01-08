package com.example.lucapp.controller;


import com.example.lucapp.dto.OrderDetailsDto;
import com.example.lucapp.service.OrderDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;

    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @PostMapping
    public ResponseEntity<OrderDetailsDto> saveOrderDetails(@Valid @RequestBody OrderDetailsDto orderDetailsDto) {
        OrderDetailsDto savedOrderDetailsDto = orderDetailsService.saveOrderDetails(orderDetailsDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOrderDetailsDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedOrderDetailsDto);
    }

    @PatchMapping("{id}")
    public ResponseEntity<OrderDetailsDto> updateOrderDetails(@Valid @RequestBody OrderDetailsDto orderDetailsDto, @PathVariable Integer id) {
        OrderDetailsDto savedOrderDetailsDto = orderDetailsService.updateOrderDetails(orderDetailsDto, id);
        return ResponseEntity.ok(savedOrderDetailsDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDetailsDto> getOrderDetailsById(@PathVariable Integer id) {
        OrderDetailsDto orderDetailsDto = orderDetailsService.findById(id);
        return ResponseEntity.ok(orderDetailsDto);
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailsDto>> getAllByOrderNumber(@RequestParam String orderNumber) {
        return ResponseEntity.ok(orderDetailsService.getAllByOrderNumber(orderNumber));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOrderDetails(@PathVariable Integer id) {
        orderDetailsService.deleteOrderDetails(id);
        return ResponseEntity.noContent().build();
    }
}
