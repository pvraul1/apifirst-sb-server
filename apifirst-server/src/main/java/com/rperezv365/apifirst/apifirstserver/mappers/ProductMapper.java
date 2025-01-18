package com.rperezv365.apifirst.apifirstserver.mappers;

import com.rperezv365.apifirst.apifirstserver.domain.Product;
import com.rperezv365.apifirst.model.ProductCreateDto;
import com.rperezv365.apifirst.model.ProductDto;
import com.rperezv365.apifirst.model.ProductPatchDto;
import com.rperezv365.apifirst.model.ProductUpdateDto;
import org.mapstruct.*;

@Mapper
@DecoratedWith(ProductMapperDecorator.class)
public interface ProductMapper {

    @Mapping(target = "categories", ignore = true)
    ProductPatchDto productToProductPatchDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "images", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void patchProduct(ProductPatchDto productPatchDto, @MappingTarget Product target);

    @Mapping(target = "categories", ignore = true)
    ProductUpdateDto productToProductUpdateDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    Product productUpdateDtoToProduct(ProductUpdateDto productUpdateDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    void updateProduct(ProductUpdateDto product, @MappingTarget Product target);

    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    Product productDtoToProduct(ProductDto productDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Product productCreateDtoToProduct(ProductCreateDto productCreateDto);

    ProductDto productToProductDto(Product product);
}
