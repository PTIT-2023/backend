package com.example.AOManager.service;

import com.example.AOManager.payload.request.ChangePasswordRequest;
import org.springframework.stereotype.Service;

@Service
public interface TaiKhoanService {


    String taoMatKhauMoi(String email);

    String doiMatKhau(ChangePasswordRequest changePasswordRequest);
}
