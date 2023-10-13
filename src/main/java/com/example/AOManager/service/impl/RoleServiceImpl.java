package com.example.AOManager.service.impl;

import com.example.AOManager.dto.RoleDto;
import com.example.AOManager.entity.RoleEntity;
import com.example.AOManager.response.ApiResponse;
import com.example.AOManager.repository.RoleRepository;
import com.example.AOManager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.AOManager.common.Message.*;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public ApiResponse<?> getAllRoles() {
        List<RoleDto> roleListDto;
        try {
            List<RoleEntity> roleList = this.roleRepository.findAll();
            roleListDto = roleList.stream().map(RoleDto::new).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_ROLE_LIST_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_ROLE_LIST_SUCCESS, roleListDto);
    }
}
