package com.rperezv365.apifirst.apifirstserver.repositories;

import com.rperezv365.apifirst.model.ProductDto;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductDto, UUID> {
}
