package com.rperezv365.apifirst.apifirstserver.services;

import com.rperezv365.apifirst.model.Order;
import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<Order> listOrders();

    Order getOrderById(UUID orderId);

}
