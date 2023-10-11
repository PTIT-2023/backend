package com.example.AOManager.service.impl;

import com.example.AOManager.dto.OrderStatusDto;
import com.example.AOManager.entity.OrderStatusEntity;
import com.example.AOManager.payload.response.ApiResponse;
import com.example.AOManager.repository.OrderStatusRepository;
import com.example.AOManager.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {
    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Override
    public ApiResponse<?> getAllOrderStatus() {
        List<OrderStatusDto> orderStatusDtoList;
        try {
            List<OrderStatusEntity> orderStatusList = this.orderStatusRepository.findAll();
            orderStatusDtoList = orderStatusList.stream().map(OrderStatusDto::new).collect(Collectors.toList());
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to get all order_status", null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Get all order_status successfully", orderStatusDtoList);
    }
}
