package com.rperezv365.apifirst.apifirstserver.controllers;

import com.rperezv365.apifirst.model.CategoryDto;
import com.rperezv365.apifirst.model.DimensionsDto;
import com.rperezv365.apifirst.model.ImageDto;
import com.rperezv365.apifirst.model.ProductDto;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class ProductControllerTest extends BaseTest{

    @Test
    void listProducts() throws Exception {
        super.mockMvc.perform(get(ProductController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", greaterThan(0)));
    }

    @Test
    void getProductById() throws Exception {
        assert super.testProduct.getId() != null;
        super.mockMvc.perform(get(ProductController.BASE_URL + "/" + super.testProduct.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(super.testProduct.getId().toString()));
    }

    @Test
    void testCreateProduct() throws Exception {
        ProductDto newProduct = ProductDto.builder()
                .description("New Product")
                .cost("5.00")
                .price("8.95")
                .categories(Collections.singletonList(CategoryDto.builder()
                        .category("New Category")
                        .description("New Category Description")
                        .build()))
                .images(Collections.singletonList(ImageDto.builder()
                        .url("http://example.com/image.jpg")
                        .altText("Image Alt Text")
                        .build()))
                .dimensions(DimensionsDto.builder()
                        .length(10)
                        .width(10)
                        .height(10)
                        .build())
                .build();

        mockMvc.perform(post(ProductController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProduct)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

    }

}