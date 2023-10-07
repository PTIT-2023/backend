package com.example.AOManager.controller;

import com.example.AOManager.entity.CustomerEntity;
import com.example.AOManager.payload.request.ChangePasswordRequest;
import com.example.AOManager.payload.request.CustomerSignupRequest;
import com.example.AOManager.payload.response.ApiResponse;
import com.example.AOManager.repository.CustomerRepository;
import com.example.AOManager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

}
