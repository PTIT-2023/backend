package com.example.AOManager.service.impl;

import com.example.AOManager.common.CheckString;
import com.example.AOManager.dto.OrderSupplierDisplayDto;
import com.example.AOManager.entity.OrderSupplierDetailEntity;
import com.example.AOManager.entity.OrderSupplierEntity;
import com.example.AOManager.payload.response.ApiResponse;
import com.example.AOManager.repository.OrderSupplierRepository;
import com.example.AOManager.service.OrderSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.AOManager.common.Message.*;

@Service
public class OrderSupplierServiceImpl implements OrderSupplierService {
    @Autowired
    OrderSupplierRepository orderSupplierRepository;

    @Override
    public ApiResponse<?> getgetOrderSupplier(String id) {
        if (CheckString.stringIsNullOrEmpty(id)) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        OrderSupplierEntity orderSupplierEntity;
        OrderSupplierDisplayDto orderSupplierDisplayDto;
        try {
            orderSupplierEntity = this.orderSupplierRepository.findById(UUID.fromString(id)).get();
            orderSupplierDisplayDto = new OrderSupplierDisplayDto();
            orderSupplierDisplayDto.setId(orderSupplierEntity.getId().toString());
            orderSupplierDisplayDto.setSupplierName(orderSupplierEntity.getSupplierName());
            orderSupplierDisplayDto.setStatus(orderSupplierEntity.getStatus());
            orderSupplierDisplayDto.setOrderDate(orderSupplierEntity.getOrderDate());
            orderSupplierDisplayDto.setDeliverydate(orderSupplierEntity.getDeliveryDate());
            orderSupplierDisplayDto.setEmployee(orderSupplierEntity.getEmployeeId().getLastName() + " " + orderSupplierEntity.getEmployeeId().getFirstName());
            List<OrderSupplierDisplayDto.Product> productList = new ArrayList<>();
            for (OrderSupplierDetailEntity orderSupplierDetailEntity : orderSupplierEntity.getOrderSupplierDetailList()) {
                OrderSupplierDisplayDto.Product product = new OrderSupplierDisplayDto.Product();
                if (orderSupplierDetailEntity.getProductId().getProductImageList().size() > 0) {
                    product.setProductImage(orderSupplierDetailEntity.getProductId().getProductImageList().get(0).getUrl());
                } else {
                    product.setProductImage(null);
                }
                product.setName(orderSupplierDetailEntity.getProductId().getName());
                product.setQuantity(orderSupplierDetailEntity.getQuantity());
                product.setUnitPrice(orderSupplierDetailEntity.getPrice());
                product.setTotalPrice(orderSupplierDetailEntity.getQuantity() * orderSupplierDetailEntity.getPrice());
                productList.add(product);
            }
            orderSupplierDisplayDto.setProductsList(productList);
            orderSupplierDisplayDto.setTotalPriceOrder(productList.stream().mapToLong(OrderSupplierDisplayDto.Product::getTotalPrice).sum());
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_ORDER_SUPPLIER_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_ORDER_SUPPLIER_SUCCESS, orderSupplierDisplayDto);
    }

    @Override
    public ApiResponse<?> getOrderSupplierList(String status, int page, int limit) {
        if (CheckString.stringIsNullOrEmpty(status) || 0 >= page || 0 >= limit) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        List<OrderSupplierDisplayDto> orderSupplierDisplayDtoList;
        try {
            Map<String, String> mapping = new HashMap<>();
            mapping.put("WAITING", "Chờ nhập hàng");
            mapping.put("IMPORTED", "Đã nhập");
            mapping.put("CANCELLED", "Đã huỷ");
            // TODO
            //orderSupplierDisplayDtoList = this.orderSupplierRepository.getOrderSupplierList(status, page - 1, limit);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_ORDER_SUPPLIER_LIST_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_ORDER_SUPPLIER_LIST_SUCCESS, orderSupplierDisplayDtoList);
    }
}
