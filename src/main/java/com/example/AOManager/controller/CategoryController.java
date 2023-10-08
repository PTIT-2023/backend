package com.example.AOManager.controller;

import com.example.AOManager.dto.CategoryDto;
import com.example.AOManager.payload.response.ApiResponse;
import com.example.AOManager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/get-all")
    public ApiResponse<?> getAllCategories() {
        List<CategoryDto> categoryList;
        try {
            categoryList = this.categoryService.getAllCategories();
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to get all categories", null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Get all categories successfully", categoryList);
    }
}
