package com.rperezv365.apifirst.apifirstserver.controllers;

import com.rperezv365.apifirst.apifirstserver.services.OrderService;
import com.rperezv365.apifirst.model.Order;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OrderController
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 07/01/2025 - 20:21
 * @since 1.17
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(OrderController.BASE_URL)
@Slf4j
public class OrderController {

    public static final String BASE_URL = "/v1/orders";

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> listOrders() {
        log.info("Listing orders (in controller) called!");

        return ResponseEntity.ok(orderService.listOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable("orderId") final UUID orderId) {
        log.info("Getting order by id (in controller) called with param: {}", orderId);

        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

}
