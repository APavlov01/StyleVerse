package com.styleverse.mapper;

import com.styleverse.dto.CategoryDTO;
import com.styleverse.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "id", target = "categoryId")
    CategoryDTO categoryToCategoryDTO(Category category);

    @Mapping(source = "categoryId", target = "id")
    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
