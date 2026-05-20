package com.example.orders.orders_microservice.mapper;

import com.example.orders.orders_microservice.dto.request.CreateOrderRequest;
import com.example.orders.orders_microservice.dto.request.OrderItemRequest;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

    OrderCreatedEvent toEvent(CreateOrderRequest request);
}
