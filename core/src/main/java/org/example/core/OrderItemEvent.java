package org.example.core;

import lombok.Data;

@Data
public class OrderItemEvent {

    private String itemId;
    private Integer quantity;
}
