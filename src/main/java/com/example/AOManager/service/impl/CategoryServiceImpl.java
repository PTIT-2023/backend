package com.example.AOManager.service.impl;

import com.example.AOManager.common.CheckString;
import com.example.AOManager.dto.CategoryDisplayDto;
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

import static com.example.AOManager.common.Message.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public ApiResponse<?> getAllCategoriesMaster() {
        List<CategoryDto> categoryDtoList;
        try {
            List<CategoryEntity> categoryList = this.categoryRepository.findAll();
            categoryDtoList = categoryList.stream().map(CategoryDto::new).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_CATEGORIES_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_CATEGORIES_SUCCESS, categoryDtoList);

    }

    @Override
    public ApiResponse<?> getAllCategoriesList() {
        List<CategoryDisplayDto> categoryDtoList;
        try {
            List<CategoryEntity> categoryList = this.categoryRepository.findAll();
            categoryDtoList = categoryList.stream().map(CategoryDisplayDto::new).collect(Collectors.toList());
            for (CategoryDisplayDto categoryDto : categoryDtoList) {
                categoryDto.setProductCount(this.categoryRepository.getProductCount(UUID.fromString(categoryDto.getId())));
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_CATEGORIES_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_CATEGORIES_SUCCESS, categoryDtoList);
    }

    @Override
    public ApiResponse<?> getCategory(String id) {
        if(CheckString.stringIsNullOrEmpty(id)) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        CategoryDto categoryDto;
        try {
            categoryDto = new CategoryDto(this.categoryRepository.findById(UUID.fromString(id)).get());
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_CATEGORY_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_CATEGORY_SUCCESS, categoryDto);
    }

    @Override
    public ApiResponse<?> createCategory(String name) {
        if(CheckString.stringIsNullOrEmpty(name)) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(name);
        try {
            this.categoryRepository.save(categoryEntity);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_CREATE_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.CREATED.value(), MSG_CREATE_SUCCESS, null);
    }

    @Override
    public ApiResponse<?> updateCategory(String id, String name) {
        if(CheckString.stringIsNullOrEmpty(id) || CheckString.stringIsNullOrEmpty(name)) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        try {
            CategoryEntity categoryEntityBef;
            try {
                categoryEntityBef = this.categoryRepository.findById(UUID.fromString(id)).get();
            } catch (Exception e) {
                System.out.println(e);
                return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), MSG_NOT_FOUND_CATEGORY_BY_ID, null);
            }
            CategoryEntity categoryEntityAft = new CategoryEntity(UUID.fromString(id), name, categoryEntityBef.getProductList());
            this.categoryRepository.save(categoryEntityAft);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_UPDATE_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), MSG_UPDATE_SUCCESS, null);
    }

    @Override
    public ApiResponse<?> deleteCategory(String id) {
        if(CheckString.stringIsNullOrEmpty(id)) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        try {
            CategoryEntity categoryEntity;
            try {
                categoryEntity = this.categoryRepository.findById(UUID.fromString(id)).get();
            } catch (Exception e) {
                System.out.println(e);
                return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), MSG_NOT_FOUND_CATEGORY_BY_ID, null);
            }
            this.categoryRepository.delete(categoryEntity);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_DELETE_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), MSG_DELETE_SUCCESS, null);
    }
}
