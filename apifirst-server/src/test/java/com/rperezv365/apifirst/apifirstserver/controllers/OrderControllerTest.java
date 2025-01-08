package com.rperezv365.apifirst.apifirstserver.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
class OrderControllerTest extends BaseTest {

    @Test
    void listOrders() throws Exception {
        super.mockMvc.perform(get(OrderController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", greaterThan(0)));
    }

    @Test
    void getOrderById() throws Exception {
        super.mockMvc.perform(get(OrderController.BASE_URL + "/" + super.testOrder.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(super.testOrder.getId().toString()));
    }

}