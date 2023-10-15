package com.example.AOManager.repository;

import com.example.AOManager.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UUID> {
    Optional<List<UserRoleEntity>> findByRoleId_Id(UUID id);

    Optional<UserRoleEntity> findByUserId_Id(UUID id);
}
