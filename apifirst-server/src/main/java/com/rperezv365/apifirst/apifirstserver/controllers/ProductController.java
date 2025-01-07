package com.rperezv365.apifirst.apifirstserver.controllers;

import com.rperezv365.apifirst.apifirstserver.services.ProductService;
import com.rperezv365.apifirst.model.Product;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProductController
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 07/01/2025 - 16:25
 * @since 1.17
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ProductController.BASE_URL)
@Slf4j
public class ProductController {

    public static final String BASE_URL = "/v1/products";

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> listProducts() {
        log.info("Listing products (in controller) called!");

        return ResponseEntity.ok(productService.listProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") final UUID productId) {
        log.info("Getting product by id (in controller) called with param: {}", productId);

        return ResponseEntity.ok(productService.getProductById(productId));
    }

}
