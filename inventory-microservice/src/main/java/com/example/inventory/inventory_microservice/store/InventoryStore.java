package com.example.inventory.inventory_microservice.store;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InventoryStore {

    private final Map<String, Integer> inventory = new HashMap<>();

    @PostConstruct
    public void init() {

        inventory.put("item-1", 10);
        inventory.put("item-2", 5);
        inventory.put("item-3", 0);
    }

    public synchronized boolean reserve(String itemId, int quantity) {

        Integer available = inventory.get(itemId);

        if (available == null || available < quantity) {
            return false;
        }

        inventory.put(itemId, available - quantity);

        return true;
    }

}
