package com.example.AOManager.repository;

import com.example.AOManager.entity.ImportFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImportFormRepository extends JpaRepository<ImportFormEntity, UUID> {
}
