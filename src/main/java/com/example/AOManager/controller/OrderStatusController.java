package com.example.AOManager.controller;


import com.example.AOManager.dto.OrderStatusDto;
import com.example.AOManager.payload.response.ApiResponse;
import com.example.AOManager.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/order-status")
public class OrderStatusController {
    @Autowired
    OrderStatusService orderStatusService;

    @GetMapping("/get-all")
    public ApiResponse<?> getAllOrderStatus() {
        List<OrderStatusDto> orderStatusList;
        try {
            orderStatusList = this.orderStatusService.getAllOrderStatus();
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to get all order_status", null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Get all order_status successfully", orderStatusList);
    }
}
