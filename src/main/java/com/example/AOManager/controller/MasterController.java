package com.example.AOManager.controller;

import com.example.AOManager.dto.CategoryDto;
import com.example.AOManager.dto.OrderStatusDto;
import com.example.AOManager.dto.RoleDto;
import com.example.AOManager.payload.response.ApiResponse;
import com.example.AOManager.service.CategoryService;
import com.example.AOManager.service.OrderStatusService;
import com.example.AOManager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/master")
public class MasterController {

    @Autowired
    RoleService roleService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    OrderStatusService orderStatusService;

    @GetMapping("/roles")
    public ApiResponse<?> getAllRole() {
        List<RoleDto> roleList;
        try {
            roleList = this.roleService.getAllRoles();
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to get all roles", null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Get all roles successfully", roleList);
    }

    @GetMapping("/categories")
    public ApiResponse<?> getAllCategories() {
        List<CategoryDto> categoryList;
        try {
            categoryList = this.categoryService.getAllCategories();
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to get all categories", null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Get all categories successfully", categoryList);
    }

    @GetMapping("/order-status")
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
