package com.example.inventory.inventory_microservice.kafka.consumer;

import com.example.inventory.inventory_microservice.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.example.core.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCreatedEventHandler {

    private final InventoryService inventoryService;

    public OrderCreatedEventHandler(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


    @KafkaListener(topics="${kafka.orders.topic}")
    public void handle(OrderCreatedEvent orderCreatedEvent){

        log.info("Received a new event with orderId: {} and correlationId: {}", orderCreatedEvent.getOrderId(), orderCreatedEvent.getCorrelationId());

        inventoryService.process(orderCreatedEvent);

    }
}
