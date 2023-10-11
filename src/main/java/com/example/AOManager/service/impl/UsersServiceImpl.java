package com.example.AOManager.service.impl;

import com.example.AOManager.dto.UsersDto;
import com.example.AOManager.entity.UsersEntity;
import com.example.AOManager.payload.request.ChangePasswordRequest;
import com.example.AOManager.payload.response.ApiResponse;
import com.example.AOManager.repository.UsersRepository;
import com.example.AOManager.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    @Lazy
    UsersService usersService;
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

    @Override
    public ApiResponse<?> getCustomerList(String roleId, int page, int limit) {
        List<UsersDto> managerList;
        try {
            managerList = this.usersService.getUsersList(roleId, page, limit);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to get customer list", null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Get customer list successfully", managerList);
    }

    @Override
    public ApiResponse<?> getEmployeeList(String roleId, int page, int limit) {
        List<UsersDto> employeeList;
        try {
            employeeList = this.usersService.getUsersList(roleId, page, limit);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to get employee list", null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Get employee list successfully", employeeList);
    }

    @Override
    public ApiResponse<?> getManagerList(String roleId, int page, int limit) {
        List<UsersDto> managerList;
        try {
            managerList = this.usersService.getUsersList(roleId, page, limit);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to get manager list", null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Get manager list successfully", managerList);
    }
}
