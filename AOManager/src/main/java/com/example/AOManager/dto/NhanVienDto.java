package com.example.AOManager.dto;

import com.example.AOManager.entity.NhanVienEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NhanVienDto implements Serializable {
    private String maNV;
    private String ho;
    private String ten;
    private String gioiTinh;
    private String diaChi;
    private String sdt;
    private String maTK;

    public NhanVienDto (NhanVienEntity nhanVienEntity) {
        this.setMaNV(nhanVienEntity.getMaNV());
        this.setHo(nhanVienEntity.getHo());
        this.setTen(nhanVienEntity.getTen());
        this.setGioiTinh(nhanVienEntity.getGioiTinh());
        this.setDiaChi(nhanVienEntity.getDiaChi());
        this.setSdt(nhanVienEntity.getSdt());
        this.setMaTK(nhanVienEntity.getMaTK().getMaTK());
    }

    public NhanVienEntity toEntity() {
        NhanVienEntity nhanVienEntity = new NhanVienEntity();
        nhanVienEntity.setMaNV(this.getMaNV());
        nhanVienEntity.setHo(this.getHo());
        nhanVienEntity.setTen(this.getTen());
        nhanVienEntity.setGioiTinh(this.getGioiTinh());
        nhanVienEntity.setDiaChi(this.getDiaChi());
        nhanVienEntity.setSdt(this.getSdt());
        return nhanVienEntity;
    }
}
