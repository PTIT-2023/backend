package com.example.AOManager.service.impl;

import com.example.AOManager.common.CheckString;
import com.example.AOManager.dto.CategoryDto;
import com.example.AOManager.entity.CategoryEntity;
import com.example.AOManager.payload.response.ApiResponse;
import com.example.AOManager.repository.CategoryRepository;
import com.example.AOManager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public ApiResponse<?> getAllCategories() {
        List<CategoryDto> categoryDtoList;
        try {
            List<CategoryEntity> categoryList = this.categoryRepository.findAll();
            categoryDtoList = categoryList.stream().map(CategoryDto::new).collect(Collectors.toList());
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to get all categories", null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Get all categories successfully", categoryDtoList);

    }

    @Override
    public ApiResponse<?> getCategory(String id) {
        if(CheckString.stringIsNullOrEmpty(id)) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Bad request", null);
        }
        CategoryDto categoryDto;
        try {
            categoryDto = new CategoryDto(this.categoryRepository.findById(UUID.fromString(id)).get());
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Faild to get category", null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Get category successfully", categoryDto);
    }

    @Override
    public ApiResponse<?> createCategory(String name) {
        if(CheckString.stringIsNullOrEmpty(name)) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Bad request", null);
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(name);
        try {
            this.categoryRepository.save(categoryEntity);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Faild to create", null);
        }
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Created", null);
    }

    @Override
    public ApiResponse<?> updateCategory(String id, String name) {
        if(CheckString.stringIsNullOrEmpty(id) || CheckString.stringIsNullOrEmpty(name)) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Bad request", null);
        }
        try {
            CategoryEntity categoryEntityBef;
            try {
                categoryEntityBef = this.categoryRepository.findById(UUID.fromString(id)).get();
            } catch (Exception e) {
                return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Not found category with this id", null);
            }
            CategoryEntity categoryEntityAft = new CategoryEntity(UUID.fromString(id), name, categoryEntityBef.getProductList());
            this.categoryRepository.save(categoryEntityAft);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Faild to update", null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Updated", null);
    }

    @Override
    public ApiResponse<?> deleteCategory(String id) {
        if(CheckString.stringIsNullOrEmpty(id)) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Bad request", null);
        }
        try {
            CategoryEntity categoryEntity;
            try {
                categoryEntity = this.categoryRepository.findById(UUID.fromString(id)).get();
            } catch (Exception e) {
                return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Not found category with this id", null);
            }
            this.categoryRepository.delete(categoryEntity);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Faild to delete", null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Deleted", null);
    }
}
