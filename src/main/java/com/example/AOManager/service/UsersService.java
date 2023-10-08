package com.example.AOManager.service;

import com.example.AOManager.payload.request.ChangePasswordRequest;

public interface UsersService {
    int changePassword(ChangePasswordRequest changePasswordRequest);
}
