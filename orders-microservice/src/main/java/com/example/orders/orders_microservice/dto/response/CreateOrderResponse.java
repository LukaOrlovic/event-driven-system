package com.example.orders.orders_microservice.dto.response;

public class CreateOrderResponse {

    private String orderId;
    private String status;

    public CreateOrderResponse(String orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }

    public CreateOrderResponse() {
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
}
