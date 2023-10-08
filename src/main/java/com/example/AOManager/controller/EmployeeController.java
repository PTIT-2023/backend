package com.example.AOManager.controller;

import com.example.AOManager.dto.UsersDto;
import com.example.AOManager.payload.response.ApiResponse;
import com.example.AOManager.repository.UsersRepository;
import com.example.AOManager.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    UsersService usersService;

    @GetMapping("/get-list")
    public ApiResponse<?> getEmployeeList(@RequestParam String roleId, @RequestParam int page, @RequestParam int limit) {
        List<UsersDto> managerList;
        try {
            managerList = this.usersService.getUsersList(roleId, page, limit);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to get employee list", null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Get employee list successfully", managerList);
    }
}
