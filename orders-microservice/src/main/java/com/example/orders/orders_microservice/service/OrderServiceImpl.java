package com.example.orders.orders_microservice.service;

import com.example.orders.orders_microservice.dto.request.CreateOrderRequest;
import com.example.orders.orders_microservice.dto.response.CreateOrderResponse;
import com.example.orders.orders_microservice.kafka.producer.OrderProducer;
import com.example.orders.orders_microservice.mapper.OrderMapper;
import com.example.orders.orders_microservice.model.OrderStatus;
import com.example.orders.orders_microservice.store.OrderStatusStore;
import org.example.core.OrderCreatedEvent;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class OrderServiceImpl implements OrderService{

    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class.getName());

    private final OrderStatusStore store;
    private final OrderProducer producer;
    private final OrderMapper mapper;

    public OrderServiceImpl(OrderStatusStore store, OrderProducer producer, OrderMapper mapper) {
        this.store = store;
        this.producer = producer;
        this.mapper = mapper;
    }

    public CreateOrderResponse createOrder(CreateOrderRequest request) {

        String correlationId = UUID.randomUUID().toString();

        LOGGER.info("Creating order=" + request.getOrderId() + ", correlationId=" + correlationId);

        store.save(request.getOrderId(), OrderStatus.CREATED);

        OrderCreatedEvent event = mapper.toEvent(request, correlationId);
        event.setCorrelationId(correlationId);

        producer.send(event);

        store.save(request.getOrderId(), OrderStatus.SENT);

        return new CreateOrderResponse(
                request.getOrderId(),
                OrderStatus.SENT.name()
        );
    }

    public CreateOrderResponse getOrder(String orderId) {

        LOGGER.info("Fetching order=" + orderId);

        OrderStatus status = store.find(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return new CreateOrderResponse(
                orderId,
                status.name()
        );
    }
}
