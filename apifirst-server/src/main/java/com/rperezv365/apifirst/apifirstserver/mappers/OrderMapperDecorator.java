package com.rperezv365.apifirst.apifirstserver.mappers;

import com.rperezv365.apifirst.apifirstserver.domain.*;
import com.rperezv365.apifirst.apifirstserver.repositories.CustomerRepository;
import com.rperezv365.apifirst.apifirstserver.repositories.ProductRepository;
import com.rperezv365.apifirst.model.OrderCreateDto;
import com.rperezv365.apifirst.model.OrderDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * OrderMapperDecorator
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 13/01/2025 - 11:33
 * @since 1.17
 */


public class OrderMapperDecorator implements OrderMapper {

    @Autowired
    @Qualifier("delegate")
    private OrderMapper delegate;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @Override
    public Order orderDtoToOrder(OrderDto orderDto) {
        return delegate.orderDtoToOrder(orderDto);
    }

    @Override
    public OrderDto orderToOrderDto(Order order) {
        OrderDto orderDto = delegate.orderToOrderDto(order);
        orderDto.getCustomer().selectedPaymentMethod(
                paymentMethodMapper.paymentMethodToPaymentMethodDto(order.getSelectedPaymentMethod()));
        return orderDto;
    }

    @Override
    public Order orderCreateDtoToOrder(OrderCreateDto orderCreateDto) {
        Customer orderCustomer = customerRepository.findById(orderCreateDto.getCustomerId()).orElseThrow();

        PaymentMethod selectedPaymentMethod = orderCustomer.getPaymentMethods().stream()
                .filter(pm -> pm.getId().equals(orderCreateDto.getSelectPaymentMethodId()))
                .findFirst()
                .orElseThrow();

        Order.OrderBuilder builder = Order.builder()
                .customer(orderCustomer)
                .selectedPaymentMethod(selectedPaymentMethod)
                .orderStatus(OrderStatusEnum.NEW);

        List<OrderLine> orderLines = new ArrayList<>();

        assert orderCreateDto.getOrderLines() != null;
        orderCreateDto.getOrderLines()
                .forEach(orderLineCreate -> {
                    Product product = productRepository.findById(orderLineCreate.getProductId()).orElseThrow();

                    orderLines.add(OrderLine.builder()
                            .product(product)
                            .orderQuantity(orderLineCreate.getOrderQuantity())
                            .build());
                });

        return builder.orderLines(orderLines).build();
    }

}
