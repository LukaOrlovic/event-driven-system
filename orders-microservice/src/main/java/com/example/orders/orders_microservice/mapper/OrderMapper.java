package com.example.orders.orders_microservice.mapper;

import com.example.orders.orders_microservice.dto.request.CreateOrderRequest;
import com.example.orders.orders_microservice.dto.request.OrderItemRequest;
import org.example.core.OrderCreatedEvent;
import org.example.core.OrderItemEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "orderId", target = "orderId")
    OrderCreatedEvent toEvent(CreateOrderRequest request);

    List<OrderItemEvent> toEventItems(List<OrderItemRequest> items);
}
