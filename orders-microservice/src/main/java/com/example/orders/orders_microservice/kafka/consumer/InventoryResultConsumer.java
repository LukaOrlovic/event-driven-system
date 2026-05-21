package com.example.orders.orders_microservice.kafka.consumer;

import com.example.orders.orders_microservice.model.OrderStatus;
import com.example.orders.orders_microservice.store.OrderStatusStore;
import org.example.core.InventoryResultEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class InventoryResultConsumer {

    private static final Logger LOGGER = Logger.getLogger(InventoryResultConsumer.class.getName());

    private final OrderStatusStore store;

    public InventoryResultConsumer(OrderStatusStore store) {
        this.store = store;
    }

    @KafkaListener(
            topics = "inventory.results",
            groupId = "orders-group"
    )
    public void consume(InventoryResultEvent event) {

        // Concatenate strings instead of using {}
        LOGGER.info("Received inventory result: orderId=" + event.getOrderId() +
                ", status=" + event.getStatus() +
                ", correlationId=" + event.getCorrelationId());

        OrderStatus status =
                "APPROVED".equals(event.getStatus())
                        ? OrderStatus.APPROVED
                        : OrderStatus.REJECTED;

        store.save(event.getOrderId(), status);

        LOGGER.info("Order updated: orderId=" + event.getOrderId() +
                " -> status=" + status);
    }
}
