package com.example.AOManager.repository;

import com.example.AOManager.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {

    Optional<CategoryEntity> findById(UUID id);

    @Query(value = "SELECT * FROM category LIMIT :limit OFFSET :page", nativeQuery = true)
    Optional<List<CategoryEntity>> getCategoriesList(int page, int limit);
}
