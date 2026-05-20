package com.example.orders.orders_microservice.service;

import com.example.orders.orders_microservice.dto.request.CreateOrderRequest;
import com.example.orders.orders_microservice.dto.response.CreateOrderResponse;
import com.example.orders.orders_microservice.kafka.producer.OrderProducer;
import com.example.orders.orders_microservice.mapper.OrderMapper;
import com.example.orders.orders_microservice.model.OrderStatus;
import com.example.orders.orders_microservice.store.OrderStatusStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.core.OrderCreatedEvent;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderStatusStore store;
    private final OrderProducer producer;
    private final OrderMapper mapper;

    public CreateOrderResponse createOrder(CreateOrderRequest request) {

        String correlationId = UUID.randomUUID().toString();

        log.info("Creating order={}, correlationId={}",
                request.getOrderId(),
                correlationId
        );

        store.save(request.getOrderId(), OrderStatus.CREATED);

        //TODO not working
        OrderCreatedEvent event = mapper.toEvent(request);
        event.setCorrelationId(correlationId);

        producer.send(event);

        store.save(request.getOrderId(), OrderStatus.SENT);

        return new CreateOrderResponse(
                request.getOrderId(),
                OrderStatus.SENT.name()
        );
    }

    public CreateOrderResponse getOrder(String orderId) {

        log.info("Fetching order={}", orderId);

        OrderStatus status = store.find(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return new CreateOrderResponse(
                orderId,
                status.name()
        );
    }
}
