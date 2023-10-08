package com.example.AOManager.service.impl;

import com.example.AOManager.dto.OrderStatusDto;
import com.example.AOManager.entity.OrderStatusEntity;
import com.example.AOManager.repository.OrderStatusRepository;
import com.example.AOManager.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {
    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Override
    public List<OrderStatusDto> getAllOrderStatus() {
        List<OrderStatusEntity> orderStatusList = this.orderStatusRepository.findAll();
        return orderStatusList.stream().map(OrderStatusDto::new).collect(Collectors.toList());
    }
}
