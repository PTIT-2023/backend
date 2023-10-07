package com.example.AOManager.service.impl;

import com.example.AOManager.dto.RoleDto;
import com.example.AOManager.entity.RoleEntity;
import com.example.AOManager.repository.RoleRepository;
import com.example.AOManager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<RoleDto> getAllRoles() {
        List<RoleEntity> roleList = this.roleRepository.findAll();
        return roleList.stream().map(RoleDto::new).collect(Collectors.toList());
    }
}
