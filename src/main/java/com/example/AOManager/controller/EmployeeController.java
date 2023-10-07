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

    @PostMapping("/signup")
    public ApiResponse<?> registEmployee(@Validated @RequestBody EmployeeSignupRequest signupRequest) {
        if(this.employeeRepository.existsByEmail(signupRequest.getEmail())) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Email has already been used", null);
        }
        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmail(signupRequest.getEmail());
        employee.setPassword(encoder.encode(signupRequest.getPassword()));
        employee.setFirstName(signupRequest.getFirstName());
        employee.setLastName(signupRequest.getLastName());
        employee.setBirthday(signupRequest.getBirthday());
        employee.setGender(signupRequest.getGender());
        employee.setAddress(signupRequest.getAddress());
        employee.setPhone(signupRequest.getPhone());
        employee.setStatus(true);
        employee.setRoleId(this.roleRepository.findById(UUID.fromString(signupRequest.getRole())).get());
        try {
            this.employeeRepository.save(employee);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", null);
        }
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Regist successfully", null);
    }

    @PostMapping("/reset-password/{email}")
    ApiResponse<?> resetPassword(@PathVariable String email) {
        int result = this.employeeService.resetPassword(email);
        if(1 == result) {
            return new ApiResponse<>(HttpStatus.OK.value(), "Reset password successfully", null);
        }
        if(2 == result){
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Faild to reset password", null);
        }
        return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Email does not exist", null);
    }

    @PutMapping("/change-password")
    ApiResponse<?> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        int result = this.employeeService.changePassword(changePasswordRequest);
        if(1 == result) {
            return new ApiResponse<>(HttpStatus.OK.value(), "Change password successfully", null);
        }
        if(2 == result) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Faild to change password", null);
        }
        if(3 == result){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "The old password is false", null);
        }
        return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "The new password does not match", null);
    }
}
