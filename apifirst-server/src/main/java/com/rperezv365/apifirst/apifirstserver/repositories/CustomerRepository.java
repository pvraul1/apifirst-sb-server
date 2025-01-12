package com.rperezv365.apifirst.apifirstserver.repositories;

import com.rperezv365.apifirst.apifirstserver.domain.Customer;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
