package com.devathon.slytherin.mappers;

import com.devathon.slytherin.DTOs.CategoryDto;
import com.devathon.slytherin.config.MapperConfig;
import com.devathon.slytherin.models.CategoryModel;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto tCategoryDto(CategoryModel categoryModel);
}
