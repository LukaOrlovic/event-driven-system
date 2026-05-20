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

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public CreateOrderResponse create(@Valid @RequestBody CreateOrderRequest request) {

        log.info("POST /orders called with orderId={}", request.getOrderId());

        return service.createOrder(request);
    }

    @GetMapping("/{orderId}")
    public CreateOrderResponse get(@PathVariable String orderId) {

        log.info("GET /orders/{} called", orderId);

        return service.getOrder(orderId);
    }
}
