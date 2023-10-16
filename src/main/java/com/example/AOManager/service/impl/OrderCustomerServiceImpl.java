package com.example.AOManager.service.impl;

import com.example.AOManager.common.CheckString;
import com.example.AOManager.dto.OrderCustomerDisplayDto;
import com.example.AOManager.entity.CartDetailEntity;
import com.example.AOManager.entity.OrderCustomerEntity;
import com.example.AOManager.entity.OrderStatusEntity;
import com.example.AOManager.repository.OrderCustomerRepository;
import com.example.AOManager.repository.OrderStatusRepository;
import com.example.AOManager.response.ApiResponse;
import com.example.AOManager.response.ApiResponseForList;
import com.example.AOManager.service.OrderCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.AOManager.common.Message.*;

@Service
public class OrderCustomerServiceImpl implements OrderCustomerService {

    @Autowired
    OrderCustomerRepository orderCustomerRepository;

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Override
    public ApiResponse<?> getOrderCustomer(String id) {
        if (CheckString.stringIsNullOrEmpty(id) || !CheckString.isValidUUID(id)) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        try {
            OrderCustomerEntity orderCustomerEntity = this.orderCustomerRepository.findById(UUID.fromString(id)).get();
            OrderCustomerDisplayDto orderCustomerDisplayDto = new OrderCustomerDisplayDto();
            orderCustomerDisplayDto.setId(orderCustomerEntity.getId().toString());
            orderCustomerDisplayDto.setOrderDate(orderCustomerEntity.getOrderDate());
            orderCustomerDisplayDto.setCustomerName(orderCustomerEntity.getCustomerId().getLastName() + " " + orderCustomerEntity.getCustomerId().getFirstName());
            orderCustomerDisplayDto.setDeliveryAddress(orderCustomerEntity.getDeliveryAddress());
            orderCustomerDisplayDto.setDeliveryEmail(orderCustomerEntity.getDeliveryEmail());
            orderCustomerDisplayDto.setDeliveryPhone(orderCustomerEntity.getDeliveryPhone());
            List<OrderCustomerDisplayDto.Product> productList = new ArrayList<>();
            for (CartDetailEntity cartDetail : orderCustomerEntity.getCartDetailList()) {
                OrderCustomerDisplayDto.Product product = new OrderCustomerDisplayDto.Product();
                if (cartDetail.getProductId().getProductImageList().size() > 0) {
                    product.setProductImage(cartDetail.getProductId().getProductImageList().get(0).getUrl());
                } else {
                    product.setProductImage(null);
                }
                product.setName(cartDetail.getProductId().getName());
                product.setQuantity(cartDetail.getQuantity());
                product.setUnitPrice(cartDetail.getPrice());
                product.setTotalPrice(cartDetail.getQuantity() * cartDetail.getPrice());
                productList.add(product);
            }
            orderCustomerDisplayDto.setProductList(productList);
            orderCustomerDisplayDto.setTotalPriceOrder(productList.stream().mapToLong(OrderCustomerDisplayDto.Product::getTotalPrice).sum());
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_ORDER_CUSTOMER_SUCCESS, orderCustomerDisplayDto);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_ORDER_CUSTOMER_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> getOrderCustomerList(String orderStatusId, int page, int limit, String keyWord) {
        if (CheckString.stringIsNullOrEmpty(orderStatusId) || !CheckString.isValidUUID(orderStatusId) || 0 >= page || 0 >= limit) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        try {
            long totalResult = this.orderCustomerRepository.getCountRecord(UUID.fromString(orderStatusId), keyWord).get().size();
            int totalPage = (int) Math.ceil((float) totalResult / limit);
            if (page > totalPage && totalPage != 0) {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
            }
            List<OrderCustomerEntity> orderCustomerEntityList = this.orderCustomerRepository.getOrderCustomerList(UUID.fromString(orderStatusId), (page - 1) * limit, limit, keyWord).get();
            List<OrderCustomerDisplayDto> orderCustomerDisplayDtoList = new ArrayList<>();
            for (OrderCustomerEntity orderCustomerEntity : orderCustomerEntityList) {
                OrderCustomerDisplayDto orderCustomerDisplayDto = new OrderCustomerDisplayDto();
                orderCustomerDisplayDto.setId(orderCustomerEntity.getId().toString());
                orderCustomerDisplayDto.setCustomerName(orderCustomerEntity.getCustomerId().getLastName() + " " + orderCustomerEntity.getCustomerId().getFirstName());
                orderCustomerDisplayDto.setDeliveryEmail(orderCustomerEntity.getDeliveryEmail());
                orderCustomerDisplayDto.setDeliveryAddress(orderCustomerEntity.getDeliveryAddress());
                orderCustomerDisplayDto.setDeliveryPhone(orderCustomerEntity.getDeliveryPhone());
                orderCustomerDisplayDto.setOrderDate(orderCustomerEntity.getOrderDate());
                orderCustomerDisplayDto.setTotalPriceOrder(0);
                orderCustomerDisplayDto.setProductList(null);
                orderCustomerDisplayDtoList.add(orderCustomerDisplayDto);
            }
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_ORDER_CUSTOMER_LIST_SUCCESS, new ApiResponseForList<>(totalResult, page, totalPage, limit, orderCustomerDisplayDtoList));
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_ORDER_CUSTOMER_LIST_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> updateStatusForOrderCustomer(String orderStatusIdTo, String id) {
        if (CheckString.stringIsNullOrEmpty(orderStatusIdTo) || CheckString.stringIsNullOrEmpty(id) || !CheckString.isValidUUID(orderStatusIdTo) || !CheckString.isValidUUID(id)) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        OrderStatusEntity orderStatusEntity;
        try {
            orderStatusEntity = this.orderStatusRepository.findById(UUID.fromString(orderStatusIdTo)).get();
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), MSG_NOT_FOUND_BY_ID, null);
        }
        OrderCustomerEntity orderCustomerEntity;
        try {
            orderCustomerEntity = this.orderCustomerRepository.findById(UUID.fromString(id)).get();
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), MSG_NOT_FOUND_BY_ID, null);
        }
        String currentStatus = orderCustomerEntity.getOrderStatusId().getName();
        switch (orderStatusEntity.getName()) {
            case "WAITING_PICKUP": {
                if (!currentStatus.equals("WAITING_CONFIRM")) {
                    return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_UPDATE_ORDER_CUSTOMER_TO_WAITING_PICKUP_FAIL, null);
                } else {
                    orderCustomerEntity.setOrderStatusId(orderStatusEntity);
                }
                break;
            }
            case "DELIVERING": {
                if (!currentStatus.equals("WAITING_PICKUP")) {
                    return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_NOT_UPDATE_ORDER_CUSTOMER_TO_DELIVERING_FAIL, null);
                } else {
                    orderCustomerEntity.setOrderStatusId(orderStatusEntity);
                }
                break;
            }
            case "DELIVERED": {
                if (!currentStatus.equals("DELIVERING")) {
                    return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_NOT_UPDATE_ORDER_CUSTOMER_TO_DELIVERED_FAIL, null);
                } else {
                    orderCustomerEntity.setOrderStatusId(orderStatusEntity);
                }
                break;
            }
            case "CANCELLED": {
                if (!currentStatus.equals("WAITING_CONFIRM")) {
                    return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_NOT_UPDATE_ORDER_CUSTOMER_TO_CANCELLED_FAIL, null);
                } else {
                    orderCustomerEntity.setOrderStatusId(orderStatusEntity);
                }
                break;
            }
            default: {
                return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_UPDATE_STATUS_ORDER_CUSTOMER_FAIL, null);
            }
        }
        try {
            this.orderCustomerRepository.save(orderCustomerEntity);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_UPDATE_STATUS_ORDER_CUSTOMER_SUCCESS, null);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_UPDATE_STATUS_ORDER_CUSTOMER_FAIL, null);
        }
    }
}
