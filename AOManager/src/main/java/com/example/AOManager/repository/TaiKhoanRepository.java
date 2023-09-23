package com.example.AOManager.repository;

import com.example.AOManager.entity.TaiKhoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoanEntity, String> {

    Optional<TaiKhoanEntity> findByMaTK(String maTK);

    Boolean existsByMaTK(String maTK);

//    TaiKhoanEntity findByNguoiQuanLy_Email(String email);
//
//    TaiKhoanEntity findByNguoiHoc_Email(String email);
}
