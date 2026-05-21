package com.example.orders.orders_microservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderItemRequest {

    @NotBlank
    private String itemId;

    @NotNull
    @Min(1)
    private Integer quantity;

    public OrderItemRequest(String itemId, Integer quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public OrderItemRequest() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
