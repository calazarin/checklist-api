package com.learning.springboot.checklistapi.dto;

import com.learning.springboot.checklistapi.entity.CategoryEntity;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class CategoryDTO {

    private String guid;

    @NotBlank(message = "Category name cannot be either null or empty")
    private String name;

    public static CategoryDTO toDTO(CategoryEntity categoryEntity){
        return CategoryDTO.builder()
                .guid(categoryEntity.getGuid())
                .name(categoryEntity.getName())
                .build();
    }
}
