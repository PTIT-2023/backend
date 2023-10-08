package com.example.AOManager.controller;

import com.example.AOManager.common.CheckString;
import com.example.AOManager.dto.CategoryDto;
import com.example.AOManager.entity.CategoryEntity;
import com.example.AOManager.payload.response.ApiResponse;
import com.example.AOManager.repository.CategoryRepository;
import com.example.AOManager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/{id}")
    public ApiResponse<?> getCategory(@PathVariable String id) {
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

    @PostMapping("/{name}")
    public ApiResponse<?> createCategory(@PathVariable String name) {
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

    @PutMapping()
    public ApiResponse<?> updateCategory(@RequestParam String id, @RequestParam String name) {
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

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteCategory(@PathVariable String id) {
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
