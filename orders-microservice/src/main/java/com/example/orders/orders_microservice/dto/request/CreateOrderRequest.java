package com.example.orders.orders_microservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;


public class CreateOrderRequest {

    @NotBlank(message = "orderId is required")
    private String orderId;

    @NotEmpty
    private List<OrderItemRequest> items;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }
}
