package com.example.AOManager.controller;

import com.example.AOManager.entity.EmployeeEntity;
import com.example.AOManager.payload.request.ChangePasswordRequest;
import com.example.AOManager.payload.request.EmployeeSignupRequest;
import com.example.AOManager.payload.response.ApiResponse;
import com.example.AOManager.repository.EmployeeRepository;
import com.example.AOManager.repository.RoleRepository;
import com.example.AOManager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;


}
