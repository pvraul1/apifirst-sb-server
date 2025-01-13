package com.rperezv365.apifirst.apifirstserver.mappers;

import com.rperezv365.apifirst.apifirstserver.domain.Category;
import com.rperezv365.apifirst.apifirstserver.domain.Product;
import com.rperezv365.apifirst.apifirstserver.repositories.CategoryRepository;
import com.rperezv365.apifirst.model.ProductCreateDto;
import com.rperezv365.apifirst.model.ProductDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * ProductMapperDecorator
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 13/01/2025 - 09:06
 * @since 1.17
 */
public abstract class ProductMapperDecorator implements ProductMapper {

    @Autowired
    @Qualifier("delegate")
    private ProductMapper productMapperDelegate;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product productDtoToProduct(ProductDto productDto) {
        return productMapperDelegate.productDtoToProduct(productDto);
    }

    @Override
    public  ProductDto productToProductDto(Product product) {
        return productMapperDelegate.productToProductDto(product);
    }

    @Override
    public Product productCreateDtoToProduct(ProductCreateDto productCreateDto) {
        if (productCreateDto != null) {
            Product product = productMapperDelegate.productCreateDtoToProduct(productCreateDto);

            if (productCreateDto.getCategories() != null) {
                List<Category> categories = new ArrayList<>();

                productCreateDto.getCategories().forEach(categoryCode ->
                        categoryRepository.findByCategoryCode(categoryCode).ifPresent(categories::add));

                product.setCategories(categories);
            }

            return product;
        }

        return null;
    }


}
