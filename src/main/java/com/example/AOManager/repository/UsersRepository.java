package com.example.AOManager.repository;

import com.example.AOManager.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<UsersEntity, UUID> {
    Optional<UsersEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
