package com.example.AOManager.service;

import com.example.AOManager.dto.UsersDto;
import com.example.AOManager.payload.request.ChangePasswordRequest;

import java.util.List;

public interface UsersService {
    int changePassword(ChangePasswordRequest changePasswordRequest);

    List<UsersDto> getUsersList(String roleId, int page, int limit);
}
