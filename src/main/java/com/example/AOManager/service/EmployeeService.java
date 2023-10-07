package com.example.AOManager.service;

import com.example.AOManager.payload.request.ChangePasswordRequest;

public interface EmployeeService {
    int resetPassword(String email);

    int changePassword(ChangePasswordRequest changePasswordRequest);
}
