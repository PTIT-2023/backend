package com.example.AOManager.service.impl;

import com.example.AOManager.common.CheckString;
import com.example.AOManager.dto.ProductDto;
import com.example.AOManager.dto.UsersDto;
import com.example.AOManager.entity.ProductEntity;
import com.example.AOManager.entity.UsersEntity;
import com.example.AOManager.repository.ProductRepository;
import com.example.AOManager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductDto> getProductsList(String categoryId) {
        List<ProductEntity> productsList;
        if(CheckString.isValidUUID(categoryId)) {
            productsList = this.productRepository.getProductsListWithCategory(UUID.fromString(categoryId));
        } else {
            productsList = this.productRepository.getProductsList();
        }
        return productsList.stream().map(ProductDto::new).collect(Collectors.toList());
    }
}
