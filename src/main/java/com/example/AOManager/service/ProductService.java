package com.example.AOManager.service;

import com.example.AOManager.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getProductsList(String categoryId, int page, int limit);
}
