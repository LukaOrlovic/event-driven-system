package com.example.orders.orders_microservice.controller;

import com.example.orders.orders_microservice.dto.request.CreateOrderRequest;
import com.example.orders.orders_microservice.dto.response.CreateOrderResponse;
import com.example.orders.orders_microservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        log.info("Received create order request: {}", request.getOrderId());

        CreateOrderResponse response = orderService.createOrder(request);

        log.info("Order {} accepted for processing", request.getOrderId());

        return ResponseEntity.accepted().body(response);
    }

    @GetMapping("/{orderId}")
    public CreateOrderResponse get(@PathVariable String orderId) {

        log.info("GET /orders/{} called", orderId);

        return orderService.getOrder(orderId);
    }
}
