package com.rperezv365.apifirst.apifirstserver.repositories;

import com.rperezv365.apifirst.apifirstserver.domain.Order;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
