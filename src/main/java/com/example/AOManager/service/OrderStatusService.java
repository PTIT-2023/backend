package com.example.AOManager.service;

import com.example.AOManager.dto.CategoryDto;
import com.example.AOManager.dto.OrderStatusDto;

import java.util.List;

public interface OrderStatusService {
    List<OrderStatusDto> getAllOrderStatus();
}
