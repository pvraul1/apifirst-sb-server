package com.rperezv365.apifirst.apifirstserver.services;

import com.rperezv365.apifirst.apifirstserver.domain.Order;
import com.rperezv365.apifirst.apifirstserver.mappers.OrderMapper;
import com.rperezv365.apifirst.apifirstserver.repositories.OrderRepository;
import com.rperezv365.apifirst.model.OrderCreateDto;
import com.rperezv365.apifirst.model.OrderDto;
import com.rperezv365.apifirst.model.OrderPatchDto;
import com.rperezv365.apifirst.model.OrderUpdateDto;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * OrderServiceImpl
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 07/01/2025 - 20:18
 * @since 1.17
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public void deleteOrder(final UUID orderId) {
        orderRepository.findById(orderId)
                .ifPresentOrElse(orderRepository::delete, () -> {
                    throw new NotFoundException("Order not found");
                });
    }

    @Override
    public List<OrderDto> listOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::orderToOrderDto)
                .toList();
    }

    @Override
    public OrderDto getOrderById(final UUID orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::orderToOrderDto)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public OrderDto createOrder(final OrderCreateDto orderCreate) {
        Order savedOrder = orderRepository.saveAndFlush(orderMapper.orderCreateDtoToOrder(orderCreate));

        return orderMapper.orderToOrderDto(savedOrder);
    }

    @Transactional
    @Override
    public OrderDto updateOrder(final UUID orderId, final OrderUpdateDto orderUpdateDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        orderMapper.updateOrder(orderUpdateDto, order);

        Order savedOrder = orderRepository.saveAndFlush(order);

        return orderMapper.orderToOrderDto(savedOrder);
    }

    @Override
    public OrderDto patchOrder(final UUID orderId, final OrderPatchDto orderPatchDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        orderMapper.patchOrder(orderPatchDto, order);

        return orderMapper.orderToOrderDto(orderRepository.saveAndFlush(order));
    }

}
