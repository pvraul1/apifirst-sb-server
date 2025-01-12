package com.rperezv365.apifirst.apifirstserver.repositories;

import com.rperezv365.apifirst.apifirstserver.domain.Customer;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {
}
