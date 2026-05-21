package com.example.inventory.inventory_microservice.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.example.core.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCreatedEventHandler {

    @KafkaListener(topics="${kafka.orders.topic}")
    public void handle(OrderCreatedEvent orderCreatedEvent){

        log.info("Received a new event: {}", orderCreatedEvent.getOrderId());
        log.info("ItemId {}", orderCreatedEvent.getItems().get(0).getItemId());

    }
}
