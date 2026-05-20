package org.example.core;

import lombok.Data;

@Data
public class InventoryResultEvent {

    private String orderId;
    private String status;
    private String reason;
    private String correlationId;
}
