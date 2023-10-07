package com.example.AOManager.controller;

import com.example.AOManager.entity.CustomerEntity;
import com.example.AOManager.entity.EmployeeEntity;
import com.example.AOManager.payload.request.ChangePasswordRequest;
import com.example.AOManager.payload.request.CustomerSignupRequest;
import com.example.AOManager.payload.request.EmployeeSignupRequest;
import com.example.AOManager.payload.request.LoginRequest;
import com.example.AOManager.payload.response.ApiResponse;
import com.example.AOManager.payload.response.JwtResponse;
import com.example.AOManager.repository.CustomerRepository;
import com.example.AOManager.repository.EmployeeRepository;
import com.example.AOManager.repository.RoleRepository;
import com.example.AOManager.security.jwt.JwtUtils;
import com.example.AOManager.security.services.CustomerDetailsImpl;
import com.example.AOManager.security.services.EmployeeDetailsImpl;
import com.example.AOManager.service.CustomerService;
import com.example.AOManager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.AOManager.common.CheckString.stringIsNullOrEmpty;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/employee/signin")
    public ApiResponse authenticateEmployee(@Valid @RequestBody LoginRequest loginRequest) {
        if (stringIsNullOrEmpty(loginRequest.getEmail()) || stringIsNullOrEmpty(loginRequest.getPassword())) {
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(), "bad request", null);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        EmployeeDetailsImpl employeeDetails = (EmployeeDetailsImpl) authentication.getPrincipal();
        List<String> roles = employeeDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new ApiResponse(HttpStatus.OK.value(), "signin successfully", new JwtResponse(jwt, employeeDetails.getUsername(), roles));
    }

    @PostMapping("/employee/signup")
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

    @PostMapping("/employee/reset-password/{email}")
    ApiResponse<?> resetPasswordEmployee(@PathVariable String email) {
        int result = this.employeeService.resetPassword(email);
        if(1 == result) {
            return new ApiResponse<>(HttpStatus.OK.value(), "Reset password successfully", null);
        }
        if(2 == result){
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Faild to reset password", null);
        }
        return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Email does not exist", null);
    }

    @PutMapping("/employee/change-password")
    ApiResponse<?> changePasswordEmployee(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
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

    @PostMapping("/customer/signin")
    public ApiResponse authenticateCustomer(@Valid @RequestBody LoginRequest loginRequest) {
        if (stringIsNullOrEmpty(loginRequest.getEmail()) || stringIsNullOrEmpty(loginRequest.getPassword())) {
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(), "bad request", null);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomerDetailsImpl customerDetails = (CustomerDetailsImpl) authentication.getPrincipal();
        return new ApiResponse(HttpStatus.OK.value(), "signin successfully", new JwtResponse(jwt, customerDetails.getUsername(), null));
    }

    @PostMapping("/customer/signup")
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

    @PostMapping("/customer/reset-password/{email}")
    ApiResponse<?> resetPasswordCustomer(@PathVariable String email) {
        String result = this.customerService.resetPassword(email);
        if("1".equals(result)) {
            return new ApiResponse<>(HttpStatus.OK.value(), "Reset password successfully", null);
        }
        if("2".equals(result)){
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Faild to reset password", null);
        }
        return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Email does not exist", null);
    }

    @PutMapping("/customer/change-password")
    ApiResponse<?> changePasswordCustomer(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
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
