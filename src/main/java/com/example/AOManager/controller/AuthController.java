package com.example.AOManager.controller;

import com.example.AOManager.entity.CustomerEntity;
import com.example.AOManager.entity.EmployeeEntity;
import com.example.AOManager.payload.request.CustomerSignupRequest;
import com.example.AOManager.payload.request.LoginRequest;
import com.example.AOManager.payload.request.EmployeeSignupRequest;
import com.example.AOManager.payload.response.ApiResponse;
import com.example.AOManager.payload.response.JwtResponse;
import com.example.AOManager.repository.CustomerRepository;
import com.example.AOManager.repository.EmployeeRepository;
import com.example.AOManager.repository.RoleRepository;
import com.example.AOManager.security.jwt.JwtUtils;
import com.example.AOManager.security.services.UserDetailsImpl;
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
    CustomerRepository customerRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin/employee")
    public ApiResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        if (stringIsNullOrEmpty(loginRequest.getEmail()) || stringIsNullOrEmpty(loginRequest.getPassword())) {
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(), "failed", null);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new ApiResponse(HttpStatus.OK.value(), "signin successfully", new JwtResponse(jwt, userDetails.getUsername(), roles));
    }

    @PostMapping("/signup/employee")
    public ApiResponse<?> registEmployee(@Validated @RequestBody EmployeeSignupRequest signupRequest) {
        if(this.employeeRepository.existsByEmail(signupRequest.getEmail())) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Email is used", null);
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

    @PostMapping("/signup/customer")
    public ApiResponse<?> registCustomer(@Validated @RequestBody CustomerSignupRequest signupRequest) {
        if(this.customerRepository.existsByEmail(signupRequest.getEmail())) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Email is used", null);
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

}
