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
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerService customerService;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/signup")
    public ApiResponse<?> registCustomer(@Validated @RequestBody CustomerSignupRequest signupRequest) {
        if(this.customerRepository.existsByEmail(signupRequest.getEmail())) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Email has already been used", null);
        }
        CustomerEntity customer = new CustomerEntity();
        customer.setEmail(signupRequest.getEmail());
        customer.setPassword(encoder.encode(signupRequest.getPassword()));
        customer.setFirstName(signupRequest.getFirstName());
        customer.setLastName(signupRequest.getLastName());
        customer.setBirthday(signupRequest.getBirthday());
        customer.setGender(signupRequest.getGender());
        customer.setAddress(signupRequest.getAddress());
        customer.setPhone(signupRequest.getPhone());
        customer.setStatus(true);
        try {
            this.customerRepository.save(customer);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", null);
        }
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Regist successfully", null);
    }

    @PostMapping("/reset-password/{email}")
    ApiResponse<?> resetPassword(@PathVariable String email) {
        String result = this.customerService.resetPassword(email);
        if("1".equals(result)) {
            return new ApiResponse<>(HttpStatus.OK.value(), "Reset password successfully", null);
        }
        if("2".equals(result)){
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Faild to reset password", null);
        }
        return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Email does not exist", null);
    }

    @PutMapping("/change-password")
    ApiResponse<?> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        int result = this.customerService.changePassword(changePasswordRequest);
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
