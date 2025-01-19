package com.rperezv365.apifirst.apifirstserver.services;

import com.rperezv365.apifirst.apifirstserver.domain.Customer;
import com.rperezv365.apifirst.apifirstserver.mappers.CustomerMapper;
import com.rperezv365.apifirst.apifirstserver.repositories.CustomerRepository;
import com.rperezv365.apifirst.model.CustomerDto;
import com.rperezv365.apifirst.model.CustomerPatchDto;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return customerRepository.findAll().stream()
                .map(customerMapper::customerToCustomerDto)
                .toList();
    }

    @Override
    public CustomerDto getCustomerById(final UUID customerId) {
        return customerMapper.customerToCustomerDto(customerRepository
                .findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found")));
    }

    @Transactional
    @Override
    public CustomerDto saveNewCustomer(final CustomerDto customer) {
        Customer savedCustomer = customerRepository.save(customerMapper.customerDtoToCustomer(customer));
        customerRepository.flush();

        return customerMapper.customerToCustomerDto(savedCustomer);
    }

    @Override
    public CustomerDto updateCustomer(final UUID customerId, final CustomerDto customer) {
        Customer customerToUpdate = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customerMapper.updateCustomer(customer, customerToUpdate);

        return customerMapper.customerToCustomerDto(customerRepository.save(customerToUpdate));
    }

    @Override
    public CustomerDto patchCustomer(final UUID customerId, final CustomerPatchDto customer) {
        Customer customerToPatch = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customerMapper.patchCustomer(customer, customerToPatch);

        return customerMapper.customerToCustomerDto(customerRepository.save(customerToPatch));
    }

    @Transactional
    @Override
    public void deleteCustomer(final UUID customerId) {
        customerRepository.findById(customerId)
                .ifPresentOrElse(customerRepository::delete, () -> {
                    throw new NotFoundException("Customer not found");
                });
    }

}
