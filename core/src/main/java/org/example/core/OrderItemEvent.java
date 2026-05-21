package org.example.core;

public class OrderItemEvent {

    private String itemId;
    private Integer quantity;

    public OrderItemEvent(String itemId, Integer quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public OrderItemEvent() {
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
