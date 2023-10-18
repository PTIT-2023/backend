package com.example.AOManager.repository;

import com.example.AOManager.entity.PriceDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PriceDetailRepository extends JpaRepository<PriceDetailEntity, UUID> {

    boolean existsByProductId_Id(UUID id);

    Optional<List<PriceDetailEntity>> findByProductId_Id(UUID productId);
}
