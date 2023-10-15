package com.example.AOManager.repository;

import com.example.AOManager.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    Optional<ProductEntity> findById(UUID id);

    Optional<List<ProductEntity>> findByCategoryId_Id(UUID id);

    @Query(value = "SELECT p.* \n" +
            "FROM product p \n" +
            "INNER JOIN category c ON p.category_id = c.id \n" +
            "WHERE (:categoryId IS NULL OR c.id = :categoryId) \n" +
            "LIMIT :limit\n" +
            "OFFSET :page", nativeQuery = true)
    List<ProductEntity> getProductsListWithCategory(UUID categoryId, int page, int limit);

    @Query(value = "SELECT p.* \n" +
            "FROM product p \n" +
            "INNER JOIN category c ON p.category_id = c.id \n" +
            "LIMIT :limit\n" +
            "OFFSET :page", nativeQuery = true)
    List<ProductEntity> getProductsList(int page, int limit);
}
