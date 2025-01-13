package com.rperezv365.apifirst.apifirstserver.mappers;

import com.rperezv365.apifirst.apifirstserver.domain.Product;
import com.rperezv365.apifirst.model.ProductCreateDto;
import com.rperezv365.apifirst.model.ProductDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
@DecoratedWith(ProductMapperDecorator.class)
public interface ProductMapper {

    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    Product productDtoToProduct(ProductDto productDto);

    ProductDto productToProductDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Product productCreateDtoToProduct(ProductCreateDto productCreateDto);

}
