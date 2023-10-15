package com.example.AOManager.service;

import com.example.AOManager.dto.UsersDto;
import com.example.AOManager.payload.request.ChangePasswordRequest;
import com.example.AOManager.request.UserRequest;
import com.example.AOManager.response.ApiResponse;

import java.util.List;

public interface UsersService {

    ApiResponse<?> changePassword(ChangePasswordRequest changePasswordRequest);

    ApiResponse<?> getUser(String id);

    List<UsersDto> getUsersList(String roleId, int page, int limit);

    ApiResponse<?> getCustomerList(String roleId, int page, int limit);

    ApiResponse<?> getEmployeeList(String roleId, int page, int limit);

    ApiResponse<?> getManagerList(String roleId, int page, int limit);

    ApiResponse<?> createUser(UserRequest createUserRequest, String role);

    ApiResponse<?> updateUser(UserRequest updateUserRequest);
}
