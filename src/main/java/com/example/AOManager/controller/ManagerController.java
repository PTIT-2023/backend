package com.example.AOManager.controller;

import com.example.AOManager.request.UserRequest;
import com.example.AOManager.response.ApiResponse;
import com.example.AOManager.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/managers")
public class ManagerController {

    @Autowired
    UsersService usersService;

    @GetMapping("/{id}")
    public ApiResponse<?> getManager(@PathVariable String id) {return this.usersService.getUser(id);}

    @GetMapping
    public ApiResponse<?> getManagerList(@RequestParam String roleId, @RequestParam int page, @RequestParam int limit) {return this.usersService.getManagerList(roleId, page, limit);}

    @PostMapping
    public ApiResponse<?> createManager(@Validated @RequestBody UserRequest createUserRequest) {return this.usersService.createUser(createUserRequest, "ROLE_MANAGER");}

    @PutMapping
    public ApiResponse<?> updateManager(@Validated @RequestBody UserRequest updateUserRequest) {return this.usersService.updateUser(updateUserRequest);}
}
