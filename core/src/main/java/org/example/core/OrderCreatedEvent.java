package org.example.core;

import lombok.Data;

import java.util.List;

@Data
public class OrderCreatedEvent {

    private String orderId;
    private List<OrderItemEvent> items;
    private String correlationId;
}
