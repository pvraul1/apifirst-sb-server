package com.rperezv365.apifirst.apifirstserver.controllers;

import com.rperezv365.apifirst.apifirstserver.domain.Customer;
import com.rperezv365.apifirst.model.*;
import java.util.Collections;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static com.atlassian.oai.validator.mockmvc.OpenApiValidationMatchers.openApi;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @DisplayName("Test Delete Conflict With Orders")
    @Test
    void testDeleteConflictWithOrders() throws Exception {
        Customer customer = customerRepository.findAll().iterator().next();

        mockMvc.perform(delete(CustomerController.BASE_URL + "/{customerId}", customer.getId()))
                .andExpect(status().isConflict())
                .andExpect(openApi().isValid(super.validator));
    }

    @Test
    void testDeleteCustomerNotFound() throws Exception {
        mockMvc.perform(delete(CustomerController.BASE_URL + "/{customerId}", UUID.randomUUID()))
                .andExpect(status().isNotFound())
                .andExpect(openApi().isValid(super.validator));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        CustomerDto customer = this.buildTestCustomerDto();
        Customer savedCustomer = customerRepository.save(customerMapper.customerDtoToCustomer(customer));

        mockMvc.perform(delete(CustomerController.BASE_URL + "/{customerId}", savedCustomer.getId()))
                .andExpect(status().isNoContent())
                .andExpect(openApi().isValid(super.validator));

        assert customerRepository.findById(savedCustomer.getId()).isEmpty();
    }

    @Transactional
    @DisplayName("Test Update CustomerNotFound")
    @Test
    void testPatchCustomerNotFound() throws Exception {
        Customer customer = customerRepository.findAll().iterator().next();

        CustomerPatchDto customerPatch = CustomerPatchDto.builder()
                .name(CustomerNamePatchDto.builder()
                        .firstName("Updated")
                        .lastName("Updated2")
                        .build())
                .paymentMethods(Collections.singletonList(CustomerPaymentMethodPatchDto.builder()
                        .id(customer.getPaymentMethods().get(0).getId())
                        .displayName("NEW NAME")
                        .build()))
                .build();

        mockMvc.perform(patch(CustomerController.BASE_URL + "/{customerId}", UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerPatch)))
                .andExpect(status().isNotFound())
                .andExpect(openApi().isValid(super.validator));
    }

    @Transactional
    @DisplayName("Test Update Customer")
    @Test
    void testPatchCustomer() throws Exception {
        Customer customer = customerRepository.findAll().iterator().next();

        CustomerPatchDto customerPatch = CustomerPatchDto.builder()
                .name(CustomerNamePatchDto.builder()
                        .firstName("Updated")
                        .lastName("Updated2")
                        .build())
                .paymentMethods(Collections.singletonList(CustomerPaymentMethodPatchDto.builder()
                        .id(customer.getPaymentMethods().get(0).getId())
                        .displayName("NEW NAME")
                        .build()))
                .build();

        mockMvc.perform(patch(CustomerController.BASE_URL + "/{customerId}", testCustomer.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerPatch)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name.firstName", equalTo("Updated")))
                .andExpect(jsonPath("$.name.lastName", equalTo("Updated2")))
                .andExpect(jsonPath("$.paymentMethods[0].displayName", equalTo("NEW NAME")))
                .andExpect(openApi().isValid(super.validator));
    }

    @Transactional
    @DisplayName("Test Update Customer Not Found")
    @Test
    void testUpdateCustomerNotFound() throws Exception {
        Customer customer = customerRepository.findAll().iterator().next();

        customer.getName().setFirstName("Updated");
        customer.getName().setLastName("Updated2");
        customer.getPaymentMethods().get(0).setDisplayName("NEW NAME");

        mockMvc.perform(put(CustomerController.BASE_URL + "/{customerId}", UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerMapper.customerToCustomerDto(customer))))
                .andExpect(status().isNotFound())
                .andExpect(openApi().isValid(super.validator));
    }

    @Transactional
    @DisplayName("Test Update Customer")
    @Test
    void testUpdateCustomer() throws Exception {
        Customer customer = customerRepository.findAll().iterator().next();

        customer.getName().setFirstName("Updated");
        customer.getName().setLastName("Updated2");
        customer.getPaymentMethods().get(0).setDisplayName("NEW NAME");

        mockMvc.perform(put(CustomerController.BASE_URL + "/{customerId}", testCustomer.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerMapper.customerToCustomerDto(customer))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name.firstName", equalTo("Updated")))
                .andExpect(jsonPath("$.name.lastName", equalTo("Updated2")))
                .andExpect(jsonPath("$.paymentMethods[0].displayName", equalTo("NEW NAME")))
                .andExpect(openApi().isValid(super.validator));
    }

    @DisplayName("Test get customer by id not found")
    @Test
    void testGetCustomerByIdNotFound() throws Exception {
        assert super.testCustomer.getId() != null;
        super.mockMvc.perform(get(CustomerController.BASE_URL + "/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(openApi().isValid(super.validator));
    }

    @DisplayName("Test get customer by id")
    @Test
    void testGetCustomerById() throws Exception {
        assert super.testCustomer.getId() != null;
        super.mockMvc.perform(get(CustomerController.BASE_URL + "/" + super.testCustomer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(super.testCustomer.getId().toString()))
                .andExpect(openApi().isValid(super.validator));
    }

    @DisplayName("Test list customers")
    @Test
    void testListCustomers() throws Exception {

        super.mockMvc.perform(get(CustomerController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", greaterThan(0)))
                .andExpect(openApi().isValid(super.validator));
    }

    @DisplayName("Test Create CustomerBadRequest")
    @Test
    void testCreateCustomerBadRequest() throws Exception {
        CustomerDto customer = this.buildTestCustomerDto();
        customer.setName(null);
        customer.setBillToAddress(null);

        mockMvc.perform(post(CustomerController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Test Create Customer")
    @Test
    void testCreateCustomer() throws Exception {
        CustomerDto customer = this.buildTestCustomerDto();

        mockMvc.perform(post(CustomerController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(openApi().isValid(super.validator));
    }

    private CustomerDto buildTestCustomerDto() {
        return CustomerDto.builder()
                .name(NameDto.builder()
                        .lastName("Doe")
                        .firstName("John")
                        .build())
                .phone("555-555-5555")
                .email("john@example.com")
                .shipToAddress(AddressDto.builder()
                        .addressLine1("123 Main St")
                        .city("Denver")
                        .state("CO")
                        .zip("80216")
                        .build())
                .billToAddress(AddressDto.builder()
                        .addressLine1("123 Main St")
                        .city("Denver")
                        .state("CO")
                        .zip("80216")
                        .build())
                .build();
    }

}
