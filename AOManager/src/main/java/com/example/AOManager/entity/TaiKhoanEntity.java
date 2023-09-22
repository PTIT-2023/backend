package com.example.AOManager.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "TAIKHOAN")
public class TaiKhoanEntity {

	@Id
    @Column(name = "MATK")
	@NotBlank(message = "Mã tài khoản không được để trống!")
    private String maTK;
	
	@Column(name = "EMAIL")
	@Email(message = "Email không hợp lệ!")
	@NotBlank(message = "Email không được để trống!")
    private String email;
    
    @Column(name = "MATKHAU")
    @NotBlank(message = "Mật khẩu không được để trống!")
    private String matKhau;
    
    @ManyToOne
    @JoinColumn(name = "MAQUYEN")
    private QuyenEntity maQuyen;
    
    @ManyToOne
	@JoinColumn(name = "MATT")
	private TrangThaiTKEntity maTT;
    
    @OneToOne(cascade = CascadeType.REFRESH, mappedBy = "maTK")
    private KhachHangEntity khachHang;
    
    @OneToOne(cascade = CascadeType.REFRESH, mappedBy = "maTK")
    private NhanVienEntity nhanVien;

	public TaiKhoanEntity() {
		super();
	}
	
	public TaiKhoanEntity(String maTK, String email, String matKhau, QuyenEntity maQuyen, TrangThaiTKEntity maTT) {
		super();
		this.maTK = maTK;
		this.email = email;
		this.matKhau = matKhau;
		this.maQuyen = maQuyen;
		this.maTT = maTT;
	}

	public TaiKhoanEntity(String maTK, String email, String matKhau, QuyenEntity maQuyen, TrangThaiTKEntity maTT,
			KhachHangEntity khachHang, NhanVienEntity nhanVien) {
		super();
		this.maTK = maTK;
		this.email = email;
		this.matKhau = matKhau;
		this.maQuyen = maQuyen;
		this.maTT = maTT;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
	}

	public String getMaTK() {
		return maTK;
	}

	public void setMaTK(String maTK) {
		this.maTK = maTK;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public QuyenEntity getMaQuyen() {
		return maQuyen;
	}

	public void setMaQuyen(QuyenEntity maQuyen) {
		this.maQuyen = maQuyen;
	}

	public TrangThaiTKEntity getMaTT() {
		return maTT;
	}

	public void setMaTT(TrangThaiTKEntity maTT) {
		this.maTT = maTT;
	}

	public KhachHangEntity getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHangEntity khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVienEntity getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVienEntity nhanVien) {
		this.nhanVien = nhanVien;
	}

}
