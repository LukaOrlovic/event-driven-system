package org.example.core;

public class InventoryResultEvent {

    private String orderId;
    private String status;
    private String reason;
    private String correlationId;

    public InventoryResultEvent(String orderId, String status, String reason, String correlationId) {
        this.orderId = orderId;
        this.status = status;
        this.reason = reason;
        this.correlationId = correlationId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
