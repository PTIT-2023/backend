package com.example.AOManager.service.impl;

import com.example.AOManager.entity.TaiKhoanEntity;
import com.example.AOManager.payload.request.ChangePasswordRequest;
import com.example.AOManager.repository.TaiKhoanRepository;
import com.example.AOManager.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;

public class TaiKhoanServiceImpl implements TaiKhoanService {

    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    @Autowired
    PasswordEncoder encoder;

    public String taoMatKhau() {
        Random generator = new Random();
        int value = generator.nextInt((999999 - 100000) + 1) + 100000;
        return String.valueOf(value); //tạo ra một chuỗi gồm 6 chữ số ngẫu nhiên
    }
    @Override
    public String taoMatKhauMoi(String email) {
        // todo
        return null;
    }

    @Override
    public String doiMatKhau(ChangePasswordRequest changePasswordRequest) {
        TaiKhoanEntity taiKhoan = this.taiKhoanRepository.findByMaTK(changePasswordRequest.getMaTK()).get();
        if(!encoder.matches(changePasswordRequest.getOldPassword(), taiKhoan.getMatKhau())) {
            return "2"; // mật khẩu cũ nhập vào không khớp
        }
        taiKhoan.setMatKhau(encoder.encode(changePasswordRequest.getNewPassword()));
        try {
            this.taiKhoanRepository.save(taiKhoan);
            return "1"; // thành công
        }
        catch (Exception e) {
            return "0"; // thất bại
        }
    }
}
