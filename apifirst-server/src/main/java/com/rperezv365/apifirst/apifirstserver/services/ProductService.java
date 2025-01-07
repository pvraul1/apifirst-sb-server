package com.rperezv365.apifirst.apifirstserver.services;

import com.rperezv365.apifirst.model.Product;
import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> listProducts();

    Product getProductById(UUID productId);

}
