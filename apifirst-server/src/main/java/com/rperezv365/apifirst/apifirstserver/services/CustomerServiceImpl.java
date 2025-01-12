package com.rperezv365.apifirst.apifirstserver.services;

import com.rperezv365.apifirst.apifirstserver.mappers.CustomerMapper;
import com.rperezv365.apifirst.apifirstserver.repositories.CustomerRepository;
import com.rperezv365.apifirst.model.CustomerDto;
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

    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDto> listCustomers() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .map(customerMapper::customerToCustomerDto)
                .toList();
    }

    @Override
    public CustomerDto getCustomerById(final UUID customerId) {
        return customerMapper.customerToCustomerDto(customerRepository
                .findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found")));
    }

    @Override
    public CustomerDto saveNewCustomer(final CustomerDto customer) {
        return customerMapper.customerToCustomerDto(customerRepository.save(customerMapper.customerDtoToCustomer(customer)));
    }

}
