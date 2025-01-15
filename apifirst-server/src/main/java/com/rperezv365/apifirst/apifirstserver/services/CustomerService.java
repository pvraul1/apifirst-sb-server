package com.rperezv365.apifirst.apifirstserver.services;

import com.rperezv365.apifirst.model.CustomerDto;
import java.util.List;
import java.util.UUID;

/**
 * CustomerService
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 07/01/2025 - 09:49
 * @since 1.17
 */
public interface CustomerService {

    List<CustomerDto> listCustomers();

    CustomerDto getCustomerById(UUID customerId);

    CustomerDto saveNewCustomer(CustomerDto customer);

    CustomerDto updateCustomer(UUID customerId, CustomerDto customer);
}
