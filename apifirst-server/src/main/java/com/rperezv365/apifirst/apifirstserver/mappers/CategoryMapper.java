package com.rperezv365.apifirst.apifirstserver.mappers;

import com.rperezv365.apifirst.apifirstserver.domain.Category;
import com.rperezv365.apifirst.model.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CategoryMapper {

    CategoryDto categoryToCategoryDto(Category category);

    @Mapping(target = "products", ignore = true)
    Category categoryDtoToCategory(CategoryDto categoryDto);

}
