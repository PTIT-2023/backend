package com.example.AOManager.service;

import com.example.AOManager.payload.response.ApiResponse;

public interface OrderSupplierService {
    ApiResponse<?> getgetOrderSupplier(String id);

    ApiResponse<?> getOrderSupplierList(String status, int page, int limit);
}
