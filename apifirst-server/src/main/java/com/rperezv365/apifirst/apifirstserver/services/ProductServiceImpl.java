package com.rperezv365.apifirst.apifirstserver.services;

import com.rperezv365.apifirst.apifirstserver.domain.Product;
import com.rperezv365.apifirst.apifirstserver.mappers.ProductMapper;
import com.rperezv365.apifirst.apifirstserver.repositories.ProductRepository;
import com.rperezv365.apifirst.model.ProductCreateDto;
import com.rperezv365.apifirst.model.ProductDto;
import com.rperezv365.apifirst.model.ProductPatchDto;
import com.rperezv365.apifirst.model.ProductUpdateDto;
import java.util.List;
import java.util.UUID;
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
    private final ProductMapper productMapper;

    @Override
    public ProductDto patchProduct(final UUID productId, final ProductPatchDto product) {
        Product productToPath = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productMapper.patchProduct(product, productToPath);

        return productMapper.productToProductDto(productRepository.save(productToPath));
    }

    @Override
    public void deleteProduct(final UUID productId) {
        productRepository.findById(productId)
                .ifPresentOrElse(productRepository::delete, () -> {
                    throw new NotFoundException("Product not found");
                });
    }

    @Override
    public ProductDto updateProduct(final UUID productId, final ProductUpdateDto product) {
        Product productToUpdate = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productMapper.updateProduct(product, productToUpdate);

        return productMapper.productToProductDto(productRepository.save(productToUpdate));
    }

    @Override
    public List<ProductDto> listProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::productToProductDto)
                .toList();
    }

    @Override
    public ProductDto getProductById(final UUID productId) {
        return productRepository.findById(productId)
                .map(productMapper::productToProductDto)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public ProductDto saveNewProduct(final ProductCreateDto product) {
        return productMapper.productToProductDto(productRepository.save(productMapper.productCreateDtoToProduct(product)));
    }

}
