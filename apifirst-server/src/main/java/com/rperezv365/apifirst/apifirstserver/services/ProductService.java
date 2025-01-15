package com.rperezv365.apifirst.apifirstserver.services;

import com.rperezv365.apifirst.model.ProductCreateDto;
import com.rperezv365.apifirst.model.ProductDto;
import com.rperezv365.apifirst.model.ProductUpdateDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductDto updateProduct(UUID productId, ProductUpdateDto product);

    List<ProductDto> listProducts();

    ProductDto getProductById(UUID productId);

    ProductDto saveNewProduct(ProductCreateDto product);

}
