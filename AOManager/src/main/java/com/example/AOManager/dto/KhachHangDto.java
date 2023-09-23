package com.example.AOManager.dto;

import com.example.AOManager.entity.KhachHangEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhachHangDto implements Serializable {
    private String maKH;
    private String ho;
    private String ten;
    private String gioiTinh;
    private String diaChi;
    private String sdt;
    private String maSoThue;
    private String maTK;

    public KhachHangDto(KhachHangEntity khachHangEntity) {
        this.setMaKH(khachHangEntity.getMaKH());
        this.setHo(khachHangEntity.getHo());
        this.setTen(khachHangEntity.getTen());
        this.setGioiTinh(khachHangEntity.getGioiTinh());
        this.setDiaChi(khachHangEntity.getDiaChi());
        this.setSdt(khachHangEntity.getSdt());
        this.setMaSoThue(khachHangEntity.getMaSoThue());
        this.setMaTK(khachHangEntity.getMaTK().getMaTK());
    }

    public KhachHangEntity toEntity() {
        KhachHangEntity khachHangEntity = new KhachHangEntity();
        khachHangEntity.setMaKH(this.getMaKH());
        khachHangEntity.setHo(this.getHo());
        khachHangEntity.setTen(this.getTen());
        khachHangEntity.setGioiTinh(this.getGioiTinh());
        khachHangEntity.setDiaChi(this.getDiaChi());
        khachHangEntity.setSdt(this.getSdt());
        khachHangEntity.setMaSoThue(this.getMaSoThue());
        return khachHangEntity;
    }
}
