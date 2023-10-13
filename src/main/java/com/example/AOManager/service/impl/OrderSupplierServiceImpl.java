package com.example.AOManager.service.impl;

import com.example.AOManager.common.CheckString;
import com.example.AOManager.dto.OrderSupplierDisplayDto;
import com.example.AOManager.entity.OrderSupplierDetailEntity;
import com.example.AOManager.entity.OrderSupplierEntity;
import com.example.AOManager.repository.OrderSupplierDetailRepository;
import com.example.AOManager.repository.ProductRepository;
import com.example.AOManager.repository.UsersRepository;
import com.example.AOManager.request.CreateOrderSupplierRequest;
import com.example.AOManager.response.ApiResponse;
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

    @Autowired
    OrderSupplierDetailRepository orderSupplierDetailRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ProductRepository productRepository;

    private String getStatusName(String key) {
        Map<String, String> mapping = new HashMap<>();
        mapping.put("WAITING", "Chờ nhập hàng");
        mapping.put("IMPORTED", "Đã nhập");
        mapping.put("CANCELLED", "Đã huỷ");
        if (mapping.containsKey(key)) {
            return mapping.get(key);
        } else {
            return null;
        }
    }

    @Override
    public ApiResponse<?> getOrderSupplier(String id) {
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
            orderSupplierDisplayDto.setStatus(this.getStatusName(orderSupplierEntity.getStatus()));
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
        List<OrderSupplierEntity> orderSupplierEntityList;
        List<OrderSupplierDisplayDto> orderSupplierDisplayDtoList = new ArrayList<>();
        try {
            orderSupplierEntityList = this.orderSupplierRepository.getOrderSupplierList(status, page - 1, limit);
            for (OrderSupplierEntity orderSupplierEntity : orderSupplierEntityList) {
                OrderSupplierDisplayDto orderSupplierDisplayDto = new OrderSupplierDisplayDto();
                orderSupplierDisplayDto.setId(orderSupplierEntity.getId().toString());
                orderSupplierDisplayDto.setSupplierName(orderSupplierEntity.getSupplierName());
                orderSupplierDisplayDto.setOrderDate(orderSupplierEntity.getOrderDate());
                orderSupplierDisplayDto.setDeliverydate(orderSupplierEntity.getDeliveryDate());
                orderSupplierDisplayDto.setStatus(orderSupplierEntity.getStatus());
                orderSupplierDisplayDto.setEmployee(orderSupplierEntity.getEmployeeId().getLastName() + " " + orderSupplierEntity.getEmployeeId().getFirstName());
                orderSupplierDisplayDto.setProductsList(null);
                orderSupplierDisplayDto.setTotalPriceOrder(0);
                orderSupplierDisplayDtoList.add(orderSupplierDisplayDto);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_ORDER_SUPPLIER_LIST_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_ORDER_SUPPLIER_LIST_SUCCESS, orderSupplierDisplayDtoList);
    }

    @Override
    public ApiResponse<?> createOrderSupplier(CreateOrderSupplierRequest createOrderSupplierRequest) {
        OrderSupplierEntity orderSupplierEntity;
        try {
            OrderSupplierEntity orderSupplierEntityToAdd = new OrderSupplierEntity();
            orderSupplierEntityToAdd.setOrderDate(createOrderSupplierRequest.getOrderDate());
            orderSupplierEntityToAdd.setDeliveryDate(createOrderSupplierRequest.getDeliveryDate());
            orderSupplierEntityToAdd.setSupplierName(createOrderSupplierRequest.getSupplierName());
            orderSupplierEntityToAdd.setStatus("WAITING");
            orderSupplierEntityToAdd.setEmployeeId(this.usersRepository.findById(UUID.fromString(createOrderSupplierRequest.getEmployeeId())).get());
            orderSupplierEntity = this.orderSupplierRepository.save(orderSupplierEntityToAdd);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_CREATE_ORDER_SUPPLIER_FAIL, null);
        }
        try {
            for (CreateOrderSupplierRequest.OrderSupplierDetail orderSupplierDetail : createOrderSupplierRequest.getOrderSupplierDetailList()) {
                OrderSupplierDetailEntity orderSupplierDetailEntity = new OrderSupplierDetailEntity();
                orderSupplierDetailEntity.setPrice(orderSupplierDetail.getPrice());
                orderSupplierDetailEntity.setQuantity(orderSupplierDetail.getQuantity());
                orderSupplierDetailEntity.setOrderSupplierId(orderSupplierEntity);
                orderSupplierDetailEntity.setProductId(this.productRepository.findById(UUID.fromString(orderSupplierDetail.getProductId())).get());
                this.orderSupplierDetailRepository.save(orderSupplierDetailEntity);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_ADD_PRODUCT_FOR_ORDER_SUPPLIER_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), MSG_CREATE_ORDER_SUPPLIER_SUCCESS, null);
    }

    @Override
    public ApiResponse<?> cancelOrderSupplier(String id) {
        if (CheckString.stringIsNullOrEmpty(id) || !CheckString.isValidUUID(id)) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        try {
            OrderSupplierEntity orderSupplierEntity;
            try {
                orderSupplierEntity = this.orderSupplierRepository.findById(UUID.fromString(id)).get();
            } catch (Exception e) {
                System.out.println(e);
                return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), MSG_NOT_FOUND_ORDER_SUPPLIER_BY_ID, null);
            }
            orderSupplierEntity.setStatus("CANCELLED");
            this.orderSupplierRepository.save(orderSupplierEntity);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_CANCEL_ORDER_SUPPLIER_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), MSG_CANCEL_ORDER_SUPPLIER_SUCCESS, null);
    }
}