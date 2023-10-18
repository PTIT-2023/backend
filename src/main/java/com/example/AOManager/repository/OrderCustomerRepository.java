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
            "WHERE (:orderStatusId IS NULL OR s.id = :orderStatusId) AND (1<>1 \n" +
            "OR CAST(o.id AS text) ILIKE CONCAT('%', :keyWord, '%') \n" +
            "OR CAST(delivery_name AS text) ILIKE CONCAT('%', :keyWord, '%') \n" +
            "OR CAST(delivery_email AS text) ILIKE CONCAT('%', :keyWord, '%') \n" +
            "OR CAST(delivery_address AS text) ILIKE CONCAT('%', :keyWord, '%') \n" +
            "OR CAST(delivery_phone AS text) ILIKE CONCAT('%', :keyWord, '%') \n" +
            "OR CAST(order_date AS text) ILIKE CONCAT('%', :keyWord, '%'))", nativeQuery = true)
    Optional<List<OrderCustomerEntity>> getCountRecord(UUID orderStatusId, String keyWord);

    @Query(value = "SELECT o.* \n" +
            "FROM order_customer o \n" +
            "INNER JOIN order_status s ON o.order_status_id = s.id \n" +
            "WHERE (:orderStatusId IS NULL OR s.id = :orderStatusId) AND (1<>1 \n" +
            "OR CAST(o.id AS text) ILIKE CONCAT('%', :keyWord, '%') \n" +
            "OR CAST(delivery_name AS text) ILIKE CONCAT('%', :keyWord, '%') \n" +
            "OR CAST(delivery_email AS text) ILIKE CONCAT('%', :keyWord, '%') \n" +
            "OR CAST(delivery_address AS text) ILIKE CONCAT('%', :keyWord, '%') \n" +
            "OR CAST(delivery_phone AS text) ILIKE CONCAT('%', :keyWord, '%') \n" +
            "OR CAST(order_date AS text) ILIKE CONCAT('%', :keyWord, '%')) \n" +
            "LIMIT :limit \n" +
            "OFFSET :page", nativeQuery = true)
    Optional<List<OrderCustomerEntity>> getOrderCustomerList(UUID orderStatusId, int page, int limit, String keyWord);
}