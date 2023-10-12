package com.example.AOManager.repository;

import com.example.AOManager.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    Optional<CategoryEntity> findById(UUID id);

    @Transactional
    @Query(value = "SELECT COUNT(p.id)\n" +
            "FROM category c\n" +
            "LEFT JOIN product p ON c.id = p.category_id \n" +
            "WHERE c.id = :id", nativeQuery = true)
    int getProductCount(UUID id);
}
