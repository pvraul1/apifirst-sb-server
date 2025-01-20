package com.rperezv365.apifirst.apifirstserver.controllers;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.rperezv365.apifirst.apifirstserver.config.OpenApiValidationConfig;
import com.rperezv365.apifirst.apifirstserver.domain.Product;
import com.rperezv365.apifirst.model.*;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static com.atlassian.oai.validator.mockmvc.OpenApiValidationMatchers.openApi;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Import(OpenApiValidationConfig.class)
class ProductControllerTest extends BaseTest {

    @Test
    void testDeleteConflictProductHasOrders() throws Exception {
        OpenApiInteractionValidator validator = OpenApiInteractionValidator
                .createForInlineApiSpecification(OpenApiValidationConfig.OA3_SPEC)
                .build();

        mockMvc.perform(delete(ProductController.BASE_URL + "/{productId}", testProduct.getId()))
                .andExpect(status().isConflict())
                .andExpect(openApi().isValid(validator));
    }

    @Test
    void testDeleteProductNotFound() throws Exception {
        mockMvc.perform(delete(ProductController.BASE_URL + "/{productId}", UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteProduct() throws Exception {
        ProductCreateDto product = this.createTestProductCreateDto();
        Product savedProduct = productRepository.save(productMapper.productCreateDtoToProduct(product));

        mockMvc.perform(delete(ProductController.BASE_URL + "/{productId}", savedProduct.getId()))
                .andExpect(status().isNoContent());

        assert productRepository.findById(savedProduct.getId()).isEmpty();
    }

    @Transactional
    @Test
    @DisplayName("Test Patch Product Not Found")
    void testPatchProductNotFound() throws Exception {

        Product product = productRepository.findAll().iterator().next();

        ProductPatchDto productPatchDto = productMapper.productToProductPatchDto(product);
        productPatchDto.setDescription("Updated Description");

        mockMvc.perform(patch(ProductController.BASE_URL + "/{productId}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productPatchDto)))
                .andExpect(status().isNotFound());
    }

    @Transactional
    @Test
    void testPatchProduct() throws Exception {

        Product product = productRepository.findAll().iterator().next();

        ProductPatchDto productPatchDto = productMapper.productToProductPatchDto(product);
        productPatchDto.setDescription("Updated Description");

        mockMvc.perform(patch(ProductController.BASE_URL + "/{productId}", product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productPatchDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", equalTo("Updated Description")));
    }

    @Transactional
    @Test
    @DisplayName("Test Update Product Not Found")
    void testUpdateProductNotFound() throws Exception {

        Product product = productRepository.findAll().iterator().next();

        ProductUpdateDto productUpdateDto = productMapper.productToProductUpdateDto(product);
        productUpdateDto.setDescription("Updated Description");

        mockMvc.perform(put(ProductController.BASE_URL + "/{productId}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productUpdateDto)))
                .andExpect(status().isNotFound());
    }

    @Transactional
    @Test
    void testUpdateProduct() throws Exception {

        Product product = productRepository.findAll().iterator().next();

        ProductUpdateDto productUpdateDto = productMapper.productToProductUpdateDto(product);
        productUpdateDto.setDescription("Updated Description");

        mockMvc.perform(put(ProductController.BASE_URL + "/{productId}", product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productUpdateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", equalTo("Updated Description")));

    }

    @Test
    void testCreateProduct() throws Exception {
        ProductCreateDto newProduct = this.createTestProductCreateDto();

        mockMvc.perform(post(ProductController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProduct)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

    }

    private ProductCreateDto createTestProductCreateDto() {
        return ProductCreateDto.builder()
                .description("New Product")
                .cost("5.00")
                .price("8.95")
                .categories(List.of("ELECTRONICS"))
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
    }

    @Test
    void listProducts() throws Exception {
        mockMvc.perform(get(ProductController.BASE_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }

    @Test
    void getProductByIdNotFound() throws Exception {
        mockMvc.perform(get(ProductController.BASE_URL + "/{productId}", UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getProductById() throws Exception {
        mockMvc.perform(get(ProductController.BASE_URL + "/{productId}", testProduct.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(testProduct.getId().toString())));
    }

}