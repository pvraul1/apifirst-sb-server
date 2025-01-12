package com.rperezv365.apifirst.apifirstserver.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rperezv365.apifirst.apifirstserver.repositories.CustomerRepository;
import com.rperezv365.apifirst.apifirstserver.repositories.OrderRepository;
import com.rperezv365.apifirst.apifirstserver.repositories.ProductRepository;
import com.rperezv365.apifirst.model.CustomerDto;
import com.rperezv365.apifirst.model.OrderDto;
import com.rperezv365.apifirst.model.ProductDto;
import jakarta.servlet.Filter;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * BaseTest
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 07/01/2025 - 20:56
 * @since 1.17
 */
public class BaseTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    Filter validationFilter;

    @Autowired
    ObjectMapper objectMapper;

    public MockMvc mockMvc;

    CustomerDto testCustomer;
    ProductDto testProduct;
    OrderDto testOrder;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(validationFilter)
                .build();
/*
        testCustomer = customerRepository.findAll().iterator().next();
        testProduct = productRepository.findAll().iterator().next();
        testOrder = orderRepository.findAll().iterator().next();

 */
    }

}
