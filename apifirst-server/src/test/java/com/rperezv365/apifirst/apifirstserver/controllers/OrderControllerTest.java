package com.rperezv365.apifirst.apifirstserver.controllers;

import com.rperezv365.apifirst.apifirstserver.domain.Order;
import com.rperezv365.apifirst.model.OrderCreateDto;
import com.rperezv365.apifirst.model.OrderLineCreateDto;
import com.rperezv365.apifirst.model.OrderUpdateDto;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class OrderControllerTest extends BaseTest {


    @Test
    @Transactional
    void testUpdateOrder() throws Exception {
        Order order = orderRepository.findAll().get(0);
        order.getOrderLines().get(0).setOrderQuantity(222);

        OrderUpdateDto orderUpdate = orderMapper.orderToUpdateDto(order);

        mockMvc.perform(put(OrderController.BASE_URL + "/{orderId}", testOrder.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderUpdate))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(testOrder.getId().toString())))
                .andExpect(jsonPath("$.orderLines[0].orderQuantity", equalTo(222)));
    }

    @Test
    void listOrders() throws Exception {
        super.mockMvc.perform(get(OrderController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", greaterThan(0)));
    }

    @Test
    void getOrderById() throws Exception {
        assert super.testOrder.getId() != null;
        super.mockMvc.perform(get(OrderController.BASE_URL + "/" + super.testOrder.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(super.testOrder.getId().toString()));
    }

    @Test
    @Transactional
    void testCreateOrder() throws Exception {
        assert testCustomer.getPaymentMethods() != null;
        OrderCreateDto orderCreate = OrderCreateDto.builder()
                .customerId(testCustomer.getId())
                .selectPaymentMethodId(testCustomer.getPaymentMethods().get(0).getId())
                .orderLines(Collections.singletonList(OrderLineCreateDto.builder()
                        .productId(testProduct.getId())
                        .orderQuantity(1)
                        .build()))
                .build();

        System.out.println(objectMapper.writeValueAsString(orderCreate));

        mockMvc.perform(post(OrderController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderCreate)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

}