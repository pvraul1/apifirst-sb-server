package com.rperezv365.apifirst.apifirstserver.services;

import com.rperezv365.apifirst.apifirstserver.repositories.ProductRepository;
import com.rperezv365.apifirst.model.ProductDto;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ProductServiceImpl
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 07/01/2025 - 16:20
 * @since 1.17
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> listProducts() {
        /*return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .toList();*/
        return null;
    }

    @Override
    public ProductDto getProductById(final UUID productId) {
        /*return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));*/
        return null;
    }

    @Override
    public ProductDto saveNewProduct(final ProductDto product) {
        // return productRepository.save(product);
        return null;
    }

}
