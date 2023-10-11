package com.example.AOManager.service.impl;

import com.example.AOManager.entity.RoleEntity;
import com.example.AOManager.entity.UserRoleEntity;
import com.example.AOManager.entity.UsersEntity;
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
import com.example.AOManager.service.AuthService;
import com.example.AOManager.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.AOManager.common.CheckString.stringIsNullOrEmpty;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    @Lazy
    UsersService usersService;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public ApiResponse sigin(LoginRequest loginRequest) {
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

    @Override
    public ApiResponse<?> signup(UserSignupRequest signupRequest) {
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

    @Override
    public ApiResponse<?> changePassword(ChangePasswordRequest changePasswordRequest) {
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
