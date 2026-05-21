package com.example.orders.orders_microservice.controller;

import com.example.orders.orders_microservice.dto.request.CreateOrderRequest;
import com.example.orders.orders_microservice.dto.response.CreateOrderResponse;
import com.example.orders.orders_microservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger LOGGER = Logger.getLogger(OrderController.class.getName());

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request) {

        LOGGER.info("Received create order request: " + request.getOrderId());

        CreateOrderResponse response = orderService.createOrder(request);

        LOGGER.info("Order " + request.getOrderId() + " accepted for processing");

        return ResponseEntity.accepted().body(response);
    }

    @GetMapping("/{orderId}")
    public CreateOrderResponse get(@PathVariable String orderId) {

        LOGGER.info("GET /orders/" + orderId + " called");

        return orderService.getOrder(orderId);
    }
}
