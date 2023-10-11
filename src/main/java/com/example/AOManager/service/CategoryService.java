package com.example.AOManager.service;

import com.example.AOManager.payload.response.ApiResponse;

import java.util.List;

public interface CategoryService {
    ApiResponse<?> getAllCategories();

    ApiResponse<?> getCategory(String id);

    ApiResponse<?> createCategory(String name);

    ApiResponse<?> updateCategory(String id, String name);

    ApiResponse<?> deleteCategory(String id);
}
