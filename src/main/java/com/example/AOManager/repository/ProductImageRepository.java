package com.example.AOManager.repository;

import com.example.AOManager.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity, UUID> {

    Optional<List<ProductImageEntity>> findByProductId_Id(UUID id);

    @Query(value = "SELECT pi.* " +
            "FROM product_image pi " +
            "LEFT JOIN product p ON pi.product_id = p.id " +
            "WHERE pi.product_id = :productId ", nativeQuery = true)
    Optional<List<ProductImageEntity>> getProductImagesByProductId(UUID productId);
}
