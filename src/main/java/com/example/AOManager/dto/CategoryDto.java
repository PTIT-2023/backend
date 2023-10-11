package com.example.AOManager.dto;

import com.example.AOManager.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private String id;
    private String name;

    public CategoryDto(CategoryEntity categoryEntity) {
        this.id = categoryEntity.getId().toString();
        this.name = categoryEntity.getName();
    }

    public CategoryEntity toEntity() {
        return new CategoryEntity(UUID.fromString(this.getId()), this.getName(), null);
    }
}
