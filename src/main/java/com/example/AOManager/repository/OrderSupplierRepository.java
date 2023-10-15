package com.example.AOManager.repository;

import com.example.AOManager.entity.OrderSupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderSupplierRepository extends JpaRepository<OrderSupplierEntity, UUID> {

    Optional<OrderSupplierEntity> findById(UUID id);

    Optional<List<OrderSupplierEntity>> findByStatus(String status);

    @Query(value = "SELECT o.* FROM order_supplier o WHERE o.status = :status LIMIT :limit OFFSET :page", nativeQuery = true)
    List<OrderSupplierEntity> getOrderSupplierList(String status, int page, int limit);
}
