package com.example.AOManager.controller;

import com.example.AOManager.payload.response.ApiResponse;
import com.example.AOManager.service.OrderSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/order-suppliers")
public class OrderSupplierController {
    @Autowired
    OrderSupplierService orderSupplierService;

    @GetMapping("/{id}")
    public ApiResponse<?> getOrderSupplier(@PathVariable String id) {return this.orderSupplierService.getgetOrderSupplier(id);}

    @GetMapping
    public ApiResponse<?> getOrderSupplierList(@RequestParam String status, @RequestParam int page, @RequestParam int limit) {return this.orderSupplierService.getOrderSupplierList(status, page, limit);}
}
