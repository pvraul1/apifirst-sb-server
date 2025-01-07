package com.rperezv365.apifirst.apifirstserver.bootstrap;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rperezv365.apifirst.apifirstserver.repositories.CustomerRepository;
import com.rperezv365.apifirst.model.Address;
import com.rperezv365.apifirst.model.Customer;
import com.rperezv365.apifirst.model.Name;
import com.rperezv365.apifirst.model.PaymentMethod;

import lombok.RequiredArgsConstructor;

/**
 * DataLoader
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 07/01/2025 - 09:12
 * @since 1.17
 */
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading Customer Data");

        Address address1 = Address.builder()
                .addressLine1("1234 W Some Street")
                .city("Some City")
                .state("FL")
                .zip("33701")
                .build();

        Customer customer1 = Customer.builder()
                .name(Name.builder()
                        .firstName("John")
                        .lastName("Thompson")
                        .build())
                .billToAddress(address1)
                .shipToAddress(address1)
                .email("john@springframework.guru")
                .phone("800-555-1212")
                .paymentMethods(List.of(PaymentMethod.builder()
                        .displayName("My Card")
                        .cardNumber(12341234)
                        .expiryMonth(12)
                        .expiryYear(26)
                        .cvv(123)
                        .build()))
                .build();

        Address address2 = Address.builder()
                .addressLine1("1234 W Some Street")
                .city("Some City")
                .state("FL")
                .zip("33701")
                .build();

        Customer customer2 = Customer.builder()
                .name(Name.builder()
                        .firstName("Jim")
                        .lastName("Smith")
                        .build())
                .billToAddress(address2)
                .shipToAddress(address2)
                .email("jim@springframework.guru")
                .phone("800-555-1212")
                .paymentMethods(List.of(PaymentMethod.builder()
                        .displayName("My Other Card")
                        .cardNumber(1234888)
                        .expiryMonth(12)
                        .expiryYear(26)
                        .cvv(456)
                        .build()))
                .build();

        customerRepository.save(customer1);
        customerRepository.save(customer2);
    }

}
