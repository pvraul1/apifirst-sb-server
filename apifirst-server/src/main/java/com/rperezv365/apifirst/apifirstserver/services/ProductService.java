package com.rperezv365.apifirst.apifirstserver.services;

import com.rperezv365.apifirst.model.ProductCreateDto;
import com.rperezv365.apifirst.model.ProductDto;
import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ProductDto> listProducts();

    ProductDto getProductById(UUID productId);

    ProductDto saveNewProduct(ProductCreateDto product);

}
