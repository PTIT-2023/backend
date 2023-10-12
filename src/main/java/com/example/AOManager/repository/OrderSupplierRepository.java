package com.example.AOManager.repository;

import com.example.AOManager.entity.OrderSupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderSupplierRepository extends JpaRepository<OrderSupplierEntity, UUID> {
    Optional<OrderSupplierEntity> findById(UUID id);
}
