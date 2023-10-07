package com.example.AOManager.repository;

import com.example.AOManager.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {
    Optional<EmployeeEntity> findByEmail(String email);
    boolean existsByEmail (String email);
    Optional<EmployeeEntity> findById(String id);
}
