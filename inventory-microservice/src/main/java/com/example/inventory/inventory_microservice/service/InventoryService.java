package com.example.inventory.inventory_microservice.service;

import com.example.inventory.inventory_microservice.store.InventoryStore;
import org.example.core.OrderCreatedEvent;
import org.example.core.OrderItemEvent;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class InventoryService {

    private static final Logger LOGGER = Logger.getLogger(InventoryService.class.getName());

    private final InventoryStore inventoryStore;

    public InventoryService(InventoryStore inventoryStore) {
        this.inventoryStore = inventoryStore;
    }

    public void process(OrderCreatedEvent event) {

        boolean success = true;

        for (OrderItemEvent item : event.getItems()) {

            boolean reserved = inventoryStore.reserve(
                    item.getItemId(),
                    item.getQuantity()
            );

            if (!reserved) {

                success = false;
                LOGGER.severe(String.format(
                        "Inventory reservation failed. correlationId=%s, orderId=%s, itemId=%s, requestedQuantity=%d",
                        event.getCorrelationId(),
                        event.getOrderId(),
                        item.getItemId(),
                        item.getQuantity()
                ));
                break;
            }
        }

        if (success) {

            LOGGER.info(String.format(
                    "Order processed successfully. correlationId=%s, orderId=%s",
                    event.getCorrelationId(),
                    event.getOrderId()
            ));
        }
    }
}
