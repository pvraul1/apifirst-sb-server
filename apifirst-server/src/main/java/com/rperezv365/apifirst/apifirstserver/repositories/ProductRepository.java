package com.rperezv365.apifirst.apifirstserver.repositories;

import com.rperezv365.apifirst.apifirstserver.domain.Product;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, UUID> {
}
