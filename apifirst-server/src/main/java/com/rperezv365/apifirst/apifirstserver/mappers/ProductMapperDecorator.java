package com.rperezv365.apifirst.apifirstserver.mappers;

import com.rperezv365.apifirst.apifirstserver.domain.Category;
import com.rperezv365.apifirst.apifirstserver.domain.Image;
import com.rperezv365.apifirst.apifirstserver.domain.Product;
import com.rperezv365.apifirst.apifirstserver.repositories.CategoryRepository;
import com.rperezv365.apifirst.apifirstserver.repositories.ImageRepository;
import com.rperezv365.apifirst.model.ProductCreateDto;
import com.rperezv365.apifirst.model.ProductDto;
import com.rperezv365.apifirst.model.ProductUpdateDto;
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

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public Product productDtoToProduct(ProductDto productDto) {
        return productMapperDelegate.productDtoToProduct(productDto);
    }

    @Override
    public  ProductDto productToProductDto(Product product) {
        return productMapperDelegate.productToProductDto(product);
    }

    @Override
    public ProductUpdateDto productToProductUpdateDto(Product product) {
        if (product != null) {
            ProductUpdateDto productUpdateDto = productMapperDelegate.productToProductUpdateDto(product);

            if (product.getCategories() != null) {
                List<String> categoryCodes = new ArrayList<>();
                product.getCategories().forEach(category -> categoryCodes.add(category.getCategoryCode()));

                productUpdateDto.setCategories(categoryCodes);
            }

            return productUpdateDto;
        }

        return null;
    }

    @Override
    public Product productUpdateDtoToProduct(ProductUpdateDto productUpdateDto) {
        if (productUpdateDto != null) {
            Product product = productMapperDelegate.productUpdateDtoToProduct(productUpdateDto);

            if (productUpdateDto.getCategories() != null) {
                List<Category> categories = this.categoryCodesToCategories(productUpdateDto.getCategories());
                product.setCategories(categories);
            }

            if (productUpdateDto.getImages() != null) {
                product.setImages(new ArrayList<>());
                productUpdateDto.getImages().forEach(imageDto -> {
                    if (imageDto.getId() != null ) {
                        imageRepository.findById(imageDto.getId()).ifPresent(image -> {
                            Image existingImage = imageRepository.findById(imageDto.getId()).get();
                            imageMapper.updateImage(imageDto, existingImage);
                            product.getImages().add(existingImage);
                        });
                    }
                });
            }

            return product;
        }

        return null;
    }

    @Override
    public void updateProduct(ProductUpdateDto product, Product target) {
        productMapperDelegate.updateProduct(product, target);

        if (product.getImages() != null && !product.getImages().isEmpty()) {
            product.getImages().forEach(imageDto -> {
                target.setImages(new ArrayList<>());

                if (imageDto.getId() != null) {
                    imageRepository.findById(imageDto.getId()).ifPresent(image -> {
                        Image existingImage = imageRepository.findById(imageDto.getId()).get();
                        imageMapper.updateImage(imageDto, existingImage);
                        target.getImages().add(existingImage);
                    });
                }
            });
        } else {
            target.setImages(new ArrayList<>());
        }

        if (product.getCategories() != null) {
            List<Category> categories = categoryCodesToCategories(product.getCategories());
            target.setCategories(categories);
        }
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

    private List<Category> categoryCodesToCategories(List<String> categoryCodes) {
        List<Category> categories = new ArrayList<>();
        categoryCodes.forEach(categoryCode -> categoryRepository.findByCategoryCode(categoryCode).ifPresent(categories::add));

        return categories;
    }

}
