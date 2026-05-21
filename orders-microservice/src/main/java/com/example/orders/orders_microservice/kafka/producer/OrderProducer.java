package com.example.orders.orders_microservice.kafka.producer;

import org.example.core.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class OrderProducer {

    private static final Logger LOGGER = Logger.getLogger(OrderProducer.class.getName());

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(OrderCreatedEvent event) {

        LOGGER.info("Sending event: orderId=" + event.getOrderId() +
                ", correlationId=" + event.getCorrelationId());



        LOGGER.info("First ItemId: " + event.getItems().get(0).getItemId());

        kafkaTemplate.send(
                "orders.created",
                event.getOrderId(),
                event
        );

        LOGGER.info("Event sent: orderId=" + event.getOrderId() +
                ", correlationId=" + event.getCorrelationId());
    }
}
