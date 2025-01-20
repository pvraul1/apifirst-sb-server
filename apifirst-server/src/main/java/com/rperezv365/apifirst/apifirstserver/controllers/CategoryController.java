package com.rperezv365.apifirst.apifirstserver.controllers;

import com.rperezv365.apifirst.model.CategoryDto;
import com.rperezv365.apifirst.apifirstserver.services.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CategoryController
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 20/01/2025 - 13:20
 * @since 1.17
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(CategoryController.BASE_URL)
@Slf4j
public class CategoryController {

    public static final String BASE_URL = "/v1/categories";

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
        log.info("Listing categories (in controller) called");

        return ResponseEntity.ok(categoryService.listCategories());
    }

}
