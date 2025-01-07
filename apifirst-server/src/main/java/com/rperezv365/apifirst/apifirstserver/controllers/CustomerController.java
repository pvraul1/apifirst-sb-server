package com.rperezv365.apifirst.apifirstserver.controllers;

import java.util.List;

import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rperezv365.apifirst.apifirstserver.services.CustomerService;
import com.rperezv365.apifirst.model.Customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * CustomerController
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 07/01/2025 - 09:46
 * @since 1.17
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(CustomerController.BASE_URL)
@Slf4j
public class CustomerController {

    public static final String BASE_URL = "/v1/customers";

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> listCustomers() {
        log.info("Listing customers (in controller) called!");

        return ResponseEntity.ok(customerService.listCustomers());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") final UUID customerId) {
        log.info("Getting customer by id (in controller) called with param: {}", customerId);

        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

}
