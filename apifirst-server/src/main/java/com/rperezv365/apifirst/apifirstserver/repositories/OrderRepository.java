package com.rperezv365.apifirst.apifirstserver.repositories;

import com.rperezv365.apifirst.model.Order;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, UUID> {
}
