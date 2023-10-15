package com.example.AOManager.repository;

import com.example.AOManager.entity.PriceDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PriceDetailRepository extends JpaRepository<PriceDetailEntity, UUID> {

    boolean existsByProductId_Id(UUID id);
}
