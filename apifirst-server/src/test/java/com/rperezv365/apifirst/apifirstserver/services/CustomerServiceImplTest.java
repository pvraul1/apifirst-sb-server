package com.rperezv365.apifirst.apifirstserver.services;

import com.rperezv365.apifirst.apifirstserver.domain.Customer;
import com.rperezv365.apifirst.apifirstserver.domain.PaymentMethod;
import com.rperezv365.apifirst.apifirstserver.repositories.CustomerRepository;
import com.rperezv365.apifirst.model.AddressDto;
import com.rperezv365.apifirst.model.CustomerDto;
import com.rperezv365.apifirst.model.NameDto;
import com.rperezv365.apifirst.model.PaymentMethodDto;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * CustomerServiceImplTest
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 12/01/2025 - 16:58
 * @since 1.17
 */
@SpringBootTest
public class CustomerServiceImplTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    @Test
    void saveNewCustomer() {
        CustomerDto customerDto = this.createCustomerDTO();

        CustomerDto savedCustomer = customerService.saveNewCustomer(customerDto);

        assertNotNull(savedCustomer);
        assertNotNull(savedCustomer.getId());

        Customer customer = customerRepository.findById(savedCustomer.getId()).orElseThrow();

        assertNotNull(customer.getPaymentMethods());

        PaymentMethod paymentMethod = customer.getPaymentMethods().get(0);

        assertEquals(customerDto.getName().getFirstName(), customer.getName().getFirstName());
    }

    @Test
    void listCustomers() {
    }

    @Test
    void getCustomerById() {
    }

    CustomerDto createCustomerDTO() {
        return CustomerDto.builder()
                .name(NameDto.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .build())
                .billToAddress(AddressDto.builder()
                        .addressLine1("1234 Main Street")
                        .city("San Diego")
                        .state("CA")
                        .zip("92101")
                        .build())
                .shipToAddress(AddressDto.builder()
                        .addressLine1("1234 Main Street")
                        .city("San Diego")
                        .state("CA")
                        .zip("92101")
                        .build())
                .email("joe@example.com")
                .phone("555-555-5555")
                .paymentMethods(Collections.singletonList(PaymentMethodDto.builder()
                        .displayName("My Card")
                        .cardNumber(1234123412)
                        .expiryMonth(12)
                        .expiryYear(2020)
                        .cvv(123).build()))
                .build();
    }

}
