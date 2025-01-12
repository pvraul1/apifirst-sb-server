package com.rperezv365.apifirst.apifirstserver.services;

import com.rperezv365.apifirst.apifirstserver.repositories.CustomerRepository;
import com.rperezv365.apifirst.apifirstserver.repositories.OrderRepository;
import com.rperezv365.apifirst.apifirstserver.repositories.ProductRepository;
import com.rperezv365.apifirst.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    public List<OrderDto> listOrders() {
        /*
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .toList();*/
        return null;
    }

    @Override
    public OrderDto getOrderById(final UUID orderId) {
        /*return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));*/
        return null;
    }

    @Override
    public OrderDto createOrder(final OrderCreateDto orderCreate) {
        /*
        CustomerDto orderCustomer = customerRepository.findById(orderCreate.getCustomerId()).orElseThrow();

        assert orderCustomer.getPaymentMethods() != null;
        OrderDto.OrderDtoBuilder builder = OrderDto.builder()
                .customer(OrderCustomerDto.builder()
                        .id(orderCustomer.getId())
                        .name(orderCustomer.getName())
                        .billToAddress(orderCustomer.getBillToAddress())
                        .shipToAddress(orderCustomer.getShipToAddress())
                        .phone(orderCustomer.getPhone())
                        .selectedPaymentMethod(orderCustomer.getPaymentMethods().stream()
                                .filter(paymentMethod -> {
                                    assert paymentMethod.getId() != null;
                                    return paymentMethod.getId()
                                            .equals(orderCreate.getSelectPaymentMethodId());
                                })
                                .findFirst().orElseThrow())
                        .build())
                .orderStatus(OrderDto.OrderStatusEnum.NEW);

        List<OrderLineDto> orderLines = new ArrayList<>();

        assert orderCreate.getOrderLines() != null;
        orderCreate.getOrderLines()
                .forEach(orderLineCreate -> {
                    ProductDto product = productRepository.findById(orderLineCreate.getProductId()).orElseThrow();

                    orderLines.add(OrderLineDto.builder()
                            .product(OrderProductDto.builder()
                                    .id(product.getId())
                                    .description(product.getDescription())
                                    .price(product.getPrice())
                                    .build())
                            .orderQuantity(orderLineCreate.getOrderQuantity())
                            .build());
                });

        return orderRepository.save(builder.orderLines(orderLines).build());*/
        return null;
    }

}
