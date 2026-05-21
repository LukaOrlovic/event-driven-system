package com.example.orders.orders_microservice.store;

import com.example.orders.orders_microservice.model.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OrderStatusStore {

    private final Map<String, OrderStatus> store = new ConcurrentHashMap<>();

    public void save(String orderId, OrderStatus status) {
        store.put(orderId, status);
    }

    public Optional<OrderStatus> find(String orderId) {
        return Optional.ofNullable(store.get(orderId));
    }
}
