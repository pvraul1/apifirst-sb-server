package com.rperezv365.apifirst.apifirstserver.services;

import com.rperezv365.apifirst.apifirstserver.repositories.CustomerRepository;
import com.rperezv365.apifirst.model.Customer;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * CustomerServiceImpl
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 07/01/2025 - 09:56
 * @since 1.17
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> listCustomers() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .toList();
    }

    @Override
    public Customer getCustomerById(final UUID customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

}
