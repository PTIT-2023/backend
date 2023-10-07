package com.example.AOManager.service;

import com.example.AOManager.payload.request.ChangePasswordRequest;

public interface CustomerService {
    String resetPassword(String email);

    int changePassword(ChangePasswordRequest changePasswordRequest);
}
