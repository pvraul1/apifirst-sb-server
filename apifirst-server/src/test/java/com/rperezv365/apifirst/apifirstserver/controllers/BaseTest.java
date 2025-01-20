package com.rperezv365.apifirst.apifirstserver.controllers;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rperezv365.apifirst.apifirstserver.domain.Customer;
import com.rperezv365.apifirst.apifirstserver.domain.Order;
import com.rperezv365.apifirst.apifirstserver.domain.Product;
import com.rperezv365.apifirst.apifirstserver.mappers.CustomerMapper;
import com.rperezv365.apifirst.apifirstserver.mappers.OrderMapper;
import com.rperezv365.apifirst.apifirstserver.mappers.ProductMapper;
import com.rperezv365.apifirst.apifirstserver.repositories.CustomerRepository;
import com.rperezv365.apifirst.apifirstserver.repositories.OrderRepository;
import com.rperezv365.apifirst.apifirstserver.repositories.ProductRepository;
import jakarta.servlet.Filter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.servlet.LogbookFilter;

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
    ProductMapper productMapper;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    public MockMvc mockMvc;

    Customer testCustomer;
    Product testProduct;
    Order testOrder;

    OpenApiInteractionValidator validator;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new LogbookFilter(Logbook.create()))
                .build();

        testCustomer = customerRepository.findAll().iterator().next();
        testProduct = productRepository.findAll().iterator().next();
        testOrder = orderRepository.findAll().iterator().next();

        String OA3_SPEC;
        try {
            ClassPathResource resource = new ClassPathResource("openapi.yaml");
            Path yamlPath = Path.of(resource.getURI());
            OA3_SPEC = Files.readString(yamlPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        validator = OpenApiInteractionValidator
                .createForInlineApiSpecification(OA3_SPEC)
                .build();

    }

}
