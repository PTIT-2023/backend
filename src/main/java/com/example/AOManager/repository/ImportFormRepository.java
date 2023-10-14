package com.example.AOManager.repository;

import com.example.AOManager.entity.ImportFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImportFormRepository extends JpaRepository<ImportFormEntity, UUID> {
    @Query(value = "SELECT i.* FROM import_form i LIMIT :limit OFFSET :page", nativeQuery = true)
    Optional<List<ImportFormEntity>> getImportFormList(int page, int limit);
}
