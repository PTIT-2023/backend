package com.example.AOManager.repository;

import com.example.AOManager.entity.RoleEntity;
import com.example.AOManager.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UUID> {
}
