package com.example.AOManager.controller;

import com.example.AOManager.dto.ProductDto;
import com.example.AOManager.dto.UsersDto;
import com.example.AOManager.payload.response.ApiResponse;
import com.example.AOManager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ApiResponse<?> getProductsList(@RequestParam String categoryId, @RequestParam String orderByPrice) {
        List<ProductDto> productsList;
        try {
            productsList = this.productService.getProductsList(categoryId, orderByPrice);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to get products list", null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Get products list successfully", productsList);
    }
}
