package com.example.AOManager.controller;

import com.example.AOManager.response.ApiResponse;
import com.example.AOManager.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/managers")
public class ManagerController {
    @Autowired
    UsersService usersService;

    @GetMapping
    public ApiResponse<?> getManagerList(@RequestParam String roleId, @RequestParam int page, @RequestParam int limit) {return this.usersService.getManagerList(roleId, page, limit);}
}
