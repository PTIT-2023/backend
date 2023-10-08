package com.example.AOManager.controller;

import com.example.AOManager.entity.RoleEntity;
import com.example.AOManager.entity.UsersEntity;
import com.example.AOManager.entity.UserRoleEntity;
import com.example.AOManager.payload.request.ChangePasswordRequest;
import com.example.AOManager.payload.request.LoginRequest;
import com.example.AOManager.payload.request.UserSignupRequest;
import com.example.AOManager.payload.response.ApiResponse;
import com.example.AOManager.payload.response.JwtResponse;
import com.example.AOManager.repository.RoleRepository;
import com.example.AOManager.repository.UserRoleRepository;
import com.example.AOManager.repository.UsersRepository;
import com.example.AOManager.security.jwt.JwtUtils;
import com.example.AOManager.security.services.UserDetailsImpl;
import com.example.AOManager.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    UsersRepository usersRepository;

    @Autowired
    UsersService usersService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/signin")
    public ApiResponse authenticateEmployee(@Valid @RequestBody LoginRequest loginRequest) {
        if (stringIsNullOrEmpty(loginRequest.getEmail()) || stringIsNullOrEmpty(loginRequest.getPassword())) {
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(), "bad request", null);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl employeeDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = employeeDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new ApiResponse(HttpStatus.OK.value(), "signin successfully", new JwtResponse(jwt, employeeDetails.getUsername(), roles));
    }

    @PostMapping("/signup")
    public ApiResponse<?> registEmployee(@Validated @RequestBody UserSignupRequest signupRequest) {
        if(this.usersRepository.existsByEmail(signupRequest.getEmail())) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Email has already been used", null);
        }
        UsersEntity user = new UsersEntity();
        user.setEmail(signupRequest.getEmail());
        user.setPassword(encoder.encode(signupRequest.getPassword()));
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setBirthday(signupRequest.getBirthday());
        user.setGender(signupRequest.getGender());
        user.setAddress(signupRequest.getAddress());
        user.setPhone(signupRequest.getPhone());
        user.setStatus(true);
        try {
            this.usersRepository.save(user);
            UsersEntity userRegistry = this.usersRepository.findByEmail(signupRequest.getEmail()).get();
            RoleEntity roleRegistry = this.roleRepository.findById(UUID.fromString(signupRequest.getRoleId())).get();
            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setUserId(userRegistry);
            userRole.setRoleId(roleRegistry);
            this.userRoleRepository.save(userRole);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Faild to regist", null);
        }
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Regist successfully", null);
    }

    @PutMapping("/change-password")
    ApiResponse<?> changePasswordCustomer(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        int result = this.usersService.changePassword(changePasswordRequest);
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
