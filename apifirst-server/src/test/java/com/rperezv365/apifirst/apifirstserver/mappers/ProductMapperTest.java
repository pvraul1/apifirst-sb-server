package com.rperezv365.apifirst.apifirstserver.mappers;

import com.rperezv365.apifirst.apifirstserver.domain.Category;
import com.rperezv365.apifirst.apifirstserver.repositories.CategoryRepository;
import com.rperezv365.apifirst.model.DimensionsDto;
import com.rperezv365.apifirst.model.ImageDto;
import com.rperezv365.apifirst.model.ProductCreateDto;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * ProductMapperTest
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 13/01/2025 - 07:43
 * @since 1.17
 */
@SpringBootTest
public class ProductMapperTest {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void dtoToProduct() {

        //fail if no category found
        Category category = categoryRepository.findByCategoryCode("ELECTRONICS").orElseThrow();
        var productCreateDto = buildProductCreateDto(category.getCategoryCode());

        var product = productMapper.productCreateDtoToProduct(productCreateDto);

        assertNotNull(product);
        assertEquals(productCreateDto.getDescription(), product.getDescription());
        assertEquals(productCreateDto.getCost(), product.getCost());
        assertEquals(productCreateDto.getPrice(), product.getPrice());
        assert productCreateDto.getDimensions() != null;
        assertEquals(productCreateDto.getDimensions().getHeight(), product.getDimensions().getHeight());
        assertEquals(productCreateDto.getDimensions().getWidth(), product.getDimensions().getWidth());
        assertEquals(productCreateDto.getDimensions().getLength(), product.getDimensions().getLength());
        assert productCreateDto.getImages() != null;
        assertEquals(productCreateDto.getImages().get(0).getUrl(), product.getImages().get(0).getUrl());
        assertEquals(productCreateDto.getImages().get(0).getAltText(), product.getImages().get(0).getAltText());
        assert productCreateDto.getCategories() != null;
        assertEquals(productCreateDto.getCategories().get(0), product.getCategories().get(0).getCategoryCode());

        //test to catch changes, fail test if fields are added
        assertEquals(9, product.getClass().getDeclaredFields().length);

    }

    ProductCreateDto buildProductCreateDto(String cat) {
        return ProductCreateDto.builder()
                .price("1.0")
                .description("description")
                .images(List.of(ImageDto.builder()
                        .url("http://example.com/image.jpg")
                        .altText("Image Alt Text")
                        .build()))
                .categories(Collections.singletonList(cat))
                .cost("1.0")
                .dimensions(DimensionsDto.builder()
                        .height(1)
                        .length(1)
                        .width(1)
                        .build())
                .build();
    }

}
