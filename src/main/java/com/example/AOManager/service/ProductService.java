package com.example.AOManager.service;

import com.example.AOManager.dto.ProductDto;
import com.example.AOManager.response.ApiResponse;

import java.util.List;

public interface ProductService {

    List<ProductDto> getProductsList(String categoryId, int page, int limit);

    ApiResponse<?> getProduct(String id);

    ApiResponse<?> createProduct(ProductDto productDto);

    ApiResponse<?> updateProduct(ProductDto productDto);

    ApiResponse<?> deleteProduct(String id);

    long getTotalRecord(String categoryId);
}
