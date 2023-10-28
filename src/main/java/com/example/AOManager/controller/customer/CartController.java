package com.example.AOManager.controller.customer;

import com.example.AOManager.response.ApiResponse;
import com.example.AOManager.service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer/cart")
public class CartController {

    @Autowired
    CartDetailService cartDetailService;

    @GetMapping("/{customerId}")
    ApiResponse<?> getCart(@PathVariable String customerId) {return this.cartDetailService.getCart(customerId);}
}
