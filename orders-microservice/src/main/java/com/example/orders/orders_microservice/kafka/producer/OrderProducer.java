package com.example.orders.orders_microservice.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.core.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(OrderCreatedEvent event) {

        log.info("Sending event: orderId={}, correlationId={}",
                event.getOrderId(),
                event.getCorrelationId()
        );

        kafkaTemplate.send(
                "orders.created",
                event.getOrderId(),
                event
        );

        log.info("Event sent: orderId={}, correlationId={}",
                event.getOrderId(),
                event.getCorrelationId()
        );
    }
}
