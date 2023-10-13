package com.example.AOManager.service.impl;

import com.example.AOManager.dto.UsersDto;
import com.example.AOManager.entity.UsersEntity;
import com.example.AOManager.payload.request.ChangePasswordRequest;
import com.example.AOManager.response.ApiResponse;
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

import static com.example.AOManager.common.Message.*;

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
    public ApiResponse<?> changePassword(ChangePasswordRequest changePasswordRequest) {
        UsersEntity user = this.usersRepository.findById(UUID.fromString(changePasswordRequest.getId())).get();
        if(!encoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_OLD_PASSWORD_NOT_TRUE, null); // mật khẩu cũ không khớp
        }
        if(!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getNewPasswordConfirm())){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_NEW_PASSWORD_NOT_MATCH, null); // Mật khẩu mới xác nhận lại không khớp
        }
        user.setPassword(encoder.encode(changePasswordRequest.getNewPassword()));
        try {
            this.usersRepository.save(user);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_CHANGE_PASSWORD_SUCCESS, null); // thành công
        }
        catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_CHANGE_PASSWORD_FAIL, null); // thất bại
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
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_CUSTOMER_LIST_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_CUSTOMER_LIST_SUCCESS, managerList);
    }

    @Override
    public ApiResponse<?> getEmployeeList(String roleId, int page, int limit) {
        List<UsersDto> employeeList;
        try {
            employeeList = this.usersService.getUsersList(roleId, page, limit);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_EMPLOYEE_LIST_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_EMPLOYEE_LIST_SUCCESS, employeeList);
    }

    @Override
    public ApiResponse<?> getManagerList(String roleId, int page, int limit) {
        List<UsersDto> managerList;
        try {
            managerList = this.usersService.getUsersList(roleId, page, limit);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_MANAGER_LIST_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_MANAGER_LIST_SUCCESS, managerList);
    }
}
