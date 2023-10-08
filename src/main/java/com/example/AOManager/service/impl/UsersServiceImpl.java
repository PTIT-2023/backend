package com.example.AOManager.service.impl;

import com.example.AOManager.entity.UsersEntity;
import com.example.AOManager.payload.request.ChangePasswordRequest;
import com.example.AOManager.repository.UserRoleRepository;
import com.example.AOManager.repository.UsersRepository;
import com.example.AOManager.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    PasswordEncoder encoder;

    @Override
    public int changePassword(ChangePasswordRequest changePasswordRequest) {
        UsersEntity user = this.usersRepository.findById(UUID.fromString(changePasswordRequest.getId())).get();
        if(!encoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            return 3; // mật khẩu cũ không khớp
        }
        if(!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getNewPasswordConfirm())){
            return 4; // Mật khẩu mới xác nhận lại không khớp
        }
        user.setPassword(encoder.encode(changePasswordRequest.getNewPassword()));
        try {
            this.usersRepository.save(user);
            return 1; // thành công
        }
        catch (Exception e) {
            return 2; // thất bại
        }
    }
}
