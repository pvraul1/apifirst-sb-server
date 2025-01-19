package com.rperezv365.apifirst.apifirstserver.controllers;

import com.rperezv365.apifirst.apifirstserver.domain.Order;
import com.rperezv365.apifirst.model.*;
import java.util.Collections;
import java.util.UUID;
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
    void testDeleteOrderNotFound() throws Exception {
        mockMvc.perform(delete(OrderController.BASE_URL + "/{orderId}", UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Transactional
    @Test
    void testDeleteOrder() throws Exception {
        OrderCreateDto order = this.createNewOrderDto();
        Order savedOrder = orderRepository.save(orderMapper.orderCreateDtoToOrder(order));

        mockMvc.perform(delete(OrderController.BASE_URL + "/{orderId}", savedOrder.getId()))
                .andExpect(status().isNoContent());

        assert orderRepository.findById(savedOrder.getId()).isEmpty();
    }

    @Test
    @Transactional
    void testPatchOrder() throws Exception {

        Order order = orderRepository.findAll().get(0);

        OrderPatchDto orderPatch = OrderPatchDto.builder()
                .orderLines(Collections.singletonList(OrderLinePatchDto.builder()
                        .id(order.getOrderLines().get(0).getId())
                        .orderQuantity(333)
                        .build()))
                .build();

        mockMvc.perform(patch(OrderController.BASE_URL + "/{orderId}", testOrder.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderPatch))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(testOrder.getId().toString())))
                .andExpect(jsonPath("$.orderLines[0].orderQuantity", equalTo(333)));
    }

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
    void getOrderByIdNotFound() throws Exception {
        assert super.testOrder.getId() != null;
        super.mockMvc.perform(get(OrderController.BASE_URL + "/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
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
        OrderCreateDto orderCreate = this.createNewOrderDto();

        System.out.println(objectMapper.writeValueAsString(orderCreate));

        mockMvc.perform(post(OrderController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderCreate)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    private OrderCreateDto createNewOrderDto() {
        return OrderCreateDto.builder()
                .customerId(testCustomer.getId())
                .selectPaymentMethodId(testCustomer.getPaymentMethods().get(0).getId())
                .orderLines(Collections.singletonList(OrderLineCreateDto.builder()
                        .productId(testProduct.getId())
                        .orderQuantity(1)
                        .build()))
                .build();
    }

}