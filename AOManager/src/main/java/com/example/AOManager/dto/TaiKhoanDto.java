package com.example.AOManager.dto;

import com.example.AOManager.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaiKhoanDto implements Serializable {

    private String maTK;
    private String email;
    private String matKhau;
    private String maQuyen;
    private String maTT;
    private String maKhachHang;
    private String maNhanVien;

    public TaiKhoanDto (TaiKhoanEntity taiKhoanEntity) {
        this.setMaTK(taiKhoanEntity.getMaTK());
        this.setEmail(taiKhoanEntity.getEmail());
        this.setMatKhau(taiKhoanEntity.getMatKhau());
        this.setMaQuyen(taiKhoanEntity.getMaQuyen().getMaQuyen());
        this.setMaTT(taiKhoanEntity.getMaTT().getMaTT());
        this.setMaKhachHang(taiKhoanEntity.getKhachHang().getMaKH());
        this.setMaNhanVien(taiKhoanEntity.getNhanVien().getMaNV());
    }

    public TaiKhoanEntity toEntity (TaiKhoanDto taiKhoanDto) {
        TaiKhoanEntity taiKhoanEntity = new TaiKhoanEntity();
        taiKhoanEntity.setMaTK(this.getMaTK());
        taiKhoanEntity.setEmail(this.getEmail());
        taiKhoanEntity.setMatKhau(this.getMatKhau());
        return taiKhoanEntity;
    }
}
