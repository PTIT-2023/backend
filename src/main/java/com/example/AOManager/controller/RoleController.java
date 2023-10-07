package com.example.AOManager.controller;

import com.example.AOManager.dto.RoleDto;
import com.example.AOManager.payload.response.ApiResponse;
import com.example.AOManager.repository.RoleRepository;
import com.example.AOManager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/get-all")
    public ApiResponse<?> getAllRole() {
        List<RoleDto> roleList = new ArrayList<>();
        try {
            roleList = this.roleService.getAllRoles();
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to get all roles", roleList);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Get all roles successfully", roleList);
    }
}
