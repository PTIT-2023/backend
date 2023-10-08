package com.example.AOManager.service.impl;

import com.example.AOManager.dto.RoleDto;
import com.example.AOManager.dto.UsersDto;
import com.example.AOManager.entity.RoleEntity;
import com.example.AOManager.entity.UsersEntity;
import com.example.AOManager.payload.request.ChangePasswordRequest;
import com.example.AOManager.repository.UserRoleRepository;
import com.example.AOManager.repository.UsersRepository;
import com.example.AOManager.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public List<UsersDto> getUsersList(String roleId, int page, int limit) {
        List<UsersEntity> managerList = this.usersRepository.getUsersList(UUID.fromString(roleId), page - 1, limit);
        return managerList.stream().map(UsersDto::new).collect(Collectors.toList());
    }
}
