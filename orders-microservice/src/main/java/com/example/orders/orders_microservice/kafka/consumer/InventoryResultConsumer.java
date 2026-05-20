package com.example.orders.orders_microservice.kafka.consumer;

import com.example.orders.orders_microservice.model.OrderStatus;
import com.example.orders.orders_microservice.store.OrderStatusStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.core.InventoryResultEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InventoryResultConsumer {

    private final OrderStatusStore store;

    @KafkaListener(
            topics = "inventory.results",
            groupId = "orders-group"
    )
    public void consume(InventoryResultEvent event) {

        log.info("Received inventory result: orderId={}, status={}, correlationId={}",
                event.getOrderId(),
                event.getStatus(),
                event.getCorrelationId()
        );

        OrderStatus status =
                "APPROVED".equals(event.getStatus())
                        ? OrderStatus.APPROVED
                        : OrderStatus.REJECTED;

        store.save(event.getOrderId(), status);

        log.info("Order updated: {} -> {}", event.getOrderId(), status);
    }
}
