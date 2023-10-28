package com.example.AOManager.service;

import com.example.AOManager.response.ApiResponse;

public interface CartDetailService {
    ApiResponse<?> getCart(String id);
}
