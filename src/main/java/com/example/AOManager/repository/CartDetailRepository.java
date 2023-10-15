package com.example.AOManager.repository;

import com.example.AOManager.entity.CartDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartDetailRepository extends JpaRepository<CartDetailEntity, UUID> {

    boolean existsByProductId_Id(UUID id);
}
