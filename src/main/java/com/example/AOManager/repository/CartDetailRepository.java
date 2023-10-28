package com.example.AOManager.repository;

import com.example.AOManager.entity.CartDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartDetailRepository extends JpaRepository<CartDetailEntity, UUID> {

    boolean existsByProductId_Id(UUID id);
    Optional<List<CartDetailEntity>> findByCustomerId_Id(UUID id);
}
