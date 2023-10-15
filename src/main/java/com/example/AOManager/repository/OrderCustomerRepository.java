package com.example.AOManager.repository;

import com.example.AOManager.entity.OrderCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderCustomerRepository extends JpaRepository<OrderCustomerEntity, UUID> {

    Optional<List<OrderCustomerEntity>> findByOrderStatusId_Id(UUID id);

    @Query(value = "SELECT o.* \n" +
            "FROM order_customer o \n" +
            "INNER JOIN order_status s ON o.order_status_id = s.id \n" +
            "WHERE (:orderStatusId IS NULL OR s.id = :orderStatusId) \n" +
            "LIMIT :limit\n" +
            "OFFSET :page", nativeQuery = true)
    Optional<List<OrderCustomerEntity>> getOrderCustomerList(UUID orderStatusId, int page, int limit);
}
