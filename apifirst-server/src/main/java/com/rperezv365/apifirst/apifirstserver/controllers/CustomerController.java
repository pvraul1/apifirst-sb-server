package com.rperezv365.apifirst.apifirstserver.controllers;

import com.rperezv365.apifirst.apifirstserver.services.CustomerService;
import com.rperezv365.apifirst.model.CustomerDto;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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

    @PostMapping
    public ResponseEntity<Void> saveNewCustomer(@RequestBody final CustomerDto customer) {
        log.info("Creating customer (in controller) called with param: {}", customer);

        CustomerDto savedCustomer = customerService.saveNewCustomer(customer);

        UriComponents uriComponents = UriComponentsBuilder.fromPath(BASE_URL + "/{customer_id}")
                .buildAndExpand(savedCustomer.getId());

        return ResponseEntity.created(URI.create(Objects.requireNonNull(uriComponents.getPath()))).build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> listCustomers() {
        log.info("Listing customers (in controller) called!");

        return ResponseEntity.ok(customerService.listCustomers());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") final UUID customerId) {
        log.info("Getting customer by id (in controller) called with param: {}", customerId);

        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

}
