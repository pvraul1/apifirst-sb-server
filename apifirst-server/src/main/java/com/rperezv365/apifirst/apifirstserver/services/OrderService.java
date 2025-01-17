package com.rperezv365.apifirst.apifirstserver.services;

import com.rperezv365.apifirst.model.OrderCreateDto;
import com.rperezv365.apifirst.model.OrderDto;
import com.rperezv365.apifirst.model.OrderUpdateDto;
import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<OrderDto> listOrders();

    OrderDto getOrderById(UUID orderId);

    OrderDto createOrder(OrderCreateDto orderCreate);

    OrderDto updateOrder(UUID orderId, OrderUpdateDto orderUpdateDto);
}
