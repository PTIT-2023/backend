package com.example.AOManager.controller;

import com.example.AOManager.payload.response.ApiResponse;
import com.example.AOManager.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    UsersService usersService;

    @GetMapping
    public ApiResponse<?> getEmployeeList(@RequestParam String roleId, @RequestParam int page, @RequestParam int limit) {return this.usersService.getEmployeeList(roleId, page, limit);}
}
