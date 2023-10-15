package com.example.AOManager.service.impl;

import com.example.AOManager.dto.UsersDto;
import com.example.AOManager.entity.UserRoleEntity;
import com.example.AOManager.entity.UsersEntity;
import com.example.AOManager.payload.request.ChangePasswordRequest;
import com.example.AOManager.repository.RoleRepository;
import com.example.AOManager.repository.UserRoleRepository;
import com.example.AOManager.request.UserRequest;
import com.example.AOManager.response.ApiResponse;
import com.example.AOManager.repository.UsersRepository;
import com.example.AOManager.response.ApiResponseForList;
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
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

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
    public ApiResponse<?> getUser(String id) {
        try {
            UsersEntity usersEntity = this.usersRepository.findById(UUID.fromString(id)).get();
            usersEntity.setPassword(this.encoder.encode(usersEntity.getPassword()));
            UsersDto usersDto = new UsersDto(usersEntity);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_USER_SUCCESS, usersDto);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_USER_FAIL, null);
        }
    }

    @Override
    public List<UsersDto> getUsersList(String roleId, int page, int limit) {
        List<UsersEntity> managerList = this.usersRepository.getUsersList(UUID.fromString(roleId), page - 1, limit);
        return managerList.stream().map(UsersDto::new).collect(Collectors.toList());
    }

    @Override
    public ApiResponse<?> getCustomerList(String roleId, int page, int limit) {
        try {
            List<UsersDto> managerList = this.usersService.getUsersList(roleId, page, limit);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_CUSTOMER_LIST_SUCCESS, managerList);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_CUSTOMER_LIST_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> getEmployeeList(String roleId, int page, int limit) {
        try {
            List<UsersDto> employeeList = this.usersService.getUsersList(roleId, page, limit);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_EMPLOYEE_LIST_SUCCESS, employeeList);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_EMPLOYEE_LIST_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> getManagerList(String roleId, int page, int limit) {
        try {
            long totalResult = this.userRoleRepository.findByRoleId_Id(UUID.fromString(roleId)).get().size();
            int totalPage = (int) Math.ceil((float)totalResult/limit);
            if(page > totalPage) {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
            }
            List<UsersDto> managerList = this.usersService.getUsersList(roleId, page, limit);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_MANAGER_LIST_SUCCESS, new ApiResponseForList<>(totalResult, page, totalPage, limit, managerList));
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_MANAGER_LIST_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> createUser(UserRequest createUserRequest, String role) {
        try {
            createUserRequest.setStatus(true);
            createUserRequest.setPassword(this.encoder.encode(createUserRequest.getPassword()));
            UsersEntity usersEntity = createUserRequest.toEntity();
            UsersEntity usersEntityCreated = this.usersRepository.save(usersEntity);
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserId(usersEntityCreated);
            userRoleEntity.setRoleId(this.roleRepository.findByName(role).get());
            this.userRoleRepository.save(userRoleEntity);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_CREATE_USER_SUCCESS, null);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_CREATE_USER_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> updateUser(UserRequest updateUserRequest) {
//        try {
//            UsersEntity usersEntity = updateUserRequest.toEntity();
//            usersEntity.setId(UUID.fromString(updateUserRequest.getId()));
//            return new ApiResponse<>(HttpStatus.OK.value(), MSG_UPDATE_USER_SUCCESS, null);
//        } catch (Exception e) {
//            System.out.println(e);
//            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_UPDATE_USER_FAIL, null);
//        }
        return null;
    }
}
