package com.rperezv365.apifirst.apifirstserver.controllers;

import com.rperezv365.apifirst.model.Address;
import com.rperezv365.apifirst.model.Customer;
import com.rperezv365.apifirst.model.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * CustomerControllerTest
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 07/01/2025 - 21:02
 * @since 1.17
 */
@SpringBootTest
public class CustomerControllerTest extends BaseTest {

    @DisplayName("Test get customer by id")
    @Test
    void testGetCustomerById() throws Exception {
        super.mockMvc.perform(get(CustomerController.BASE_URL + "/" + super.testCustomer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(super.testCustomer.getId().toString()));
    }

    @DisplayName("Test list customers")
    @Test
    void testListCustomers() throws Exception {

        super.mockMvc.perform(get(CustomerController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", greaterThan(0)));

    }

    @DisplayName("Test Create Customer")
    @Test
    void testCreateCustomer() throws Exception {
        Customer customer = Customer.builder()
                .name(Name.builder()
                        .lastName("Doe")
                        .firstName("John")
                        .build())
                .phone("555-555-5555")
                .email("john@example.com")
                .shipToAddress(Address.builder()
                        .addressLine1("123 Main St")
                        .city("Denver")
                        .state("CO")
                        .zip("80216")
                        .build())
                .billToAddress(Address.builder()
                        .addressLine1("123 Main St")
                        .city("Denver")
                        .state("CO")
                        .zip("80216")
                        .build())
                .build();

        mockMvc.perform(post(CustomerController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

}
