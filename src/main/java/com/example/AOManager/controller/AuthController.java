package com.example.AOManager.controller;

import com.example.AOManager.payload.request.ChangePasswordRequest;
import com.example.AOManager.payload.request.LoginRequest;
import com.example.AOManager.payload.request.UserSignupRequest;
import com.example.AOManager.response.ApiResponse;
import com.example.AOManager.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    @Lazy
    AuthService authService;

    @PostMapping("/signin")
    public ApiResponse authenticateEmployee(@Valid @RequestBody LoginRequest loginRequest) {return this.authService.sigin(loginRequest);}

    @PostMapping("/signup")
    public ApiResponse<?> registEmployee(@Validated @RequestBody UserSignupRequest signupRequest) {return this.authService.signup(signupRequest);}

    @PutMapping("/change-password")
    ApiResponse<?> changePasswordCustomer(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {return this.authService.changePassword(changePasswordRequest);}
}
