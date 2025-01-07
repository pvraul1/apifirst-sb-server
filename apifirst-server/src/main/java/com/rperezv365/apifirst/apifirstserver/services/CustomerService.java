package com.rperezv365.apifirst.apifirstserver.services;

import com.rperezv365.apifirst.model.Customer;
import java.util.List;

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

    List<Customer> listCustomers();

}
