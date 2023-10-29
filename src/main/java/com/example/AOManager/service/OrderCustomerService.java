package com.example.AOManager.service;

import com.example.AOManager.response.ApiResponse;

public interface OrderCustomerService {

    ApiResponse<?> getOrderCustomer(String id);

    ApiResponse<?> getOrderCustomerList(String orderStatusId, int page, int limit, String keyWord);

    ApiResponse<?> updateStatusForOrderCustomer(String orderStatusIdTo, String id);

    ApiResponse<?> getOrdersListForCustomerByStatusId(String orderStatusId, int limit);

    ApiResponse<?> cancelOrder(String orderCustomerId);
}
