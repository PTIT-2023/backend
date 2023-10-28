package com.example.AOManager.controller.customer;

import com.example.AOManager.response.ApiResponse;
import com.example.AOManager.service.OrderCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer/order")
public class OrderController {

    @Autowired
    OrderCustomerService orderCustomerService;

    @GetMapping
    ApiResponse<?> getOrdersListForCustomerByStatusId(@RequestParam String orderStatusId, @RequestParam int limit) {return this.orderCustomerService.getOrdersListForCustomerByStatusId(orderStatusId, limit);}

    @GetMapping("/{orderCustomerId}")
    ApiResponse<?> getOrderDetail(@PathVariable String orderCustomerId) {return this.orderCustomerService.getOrderCustomer(orderCustomerId);}
}
