package com.example.AOManager.repository;

import com.example.AOManager.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<UsersEntity, UUID> {

    Optional<UsersEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query(value = "SELECT u.* \n" +
                    "FROM users u\n" +
                    "INNER JOIN user_role ur ON u.id = ur.user_id\n" +
                    "INNER JOIN role r ON ur.role_id = r.id\n" +
                    "WHERE r.id = :roleId\n" +
                    "ORDER BY u.first_name\n" +
                    "LIMIT :limit\n" +
                    "OFFSET :page", nativeQuery = true)
    List<UsersEntity> getUsersList(UUID roleId, int page, int limit);
}
