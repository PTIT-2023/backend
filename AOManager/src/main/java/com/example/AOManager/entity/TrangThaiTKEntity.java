package com.example.AOManager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TRANGTHAITK")
public class TrangThaiTKEntity {
	
	@Id
	@Column(name = "MATT")
	private String maTT;
	
	@Column(name = "TENTT")
	private String tenTT;
	
	@OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maTT")
	private List<TaiKhoanEntity> taiKhoan;

	public TrangThaiTKEntity() {
		super();
	}
	
	public TrangThaiTKEntity(String maTT, String tenTT) {
		super();
		this.maTT = maTT;
		this.tenTT = tenTT;
	}

	public TrangThaiTKEntity(String maTT, String tenTT, List<TaiKhoanEntity> taiKhoan) {
		super();
		this.maTT = maTT;
		this.tenTT = tenTT;
		this.taiKhoan = taiKhoan;
	}

	public String getmaTT() {
		return maTT;
	}

	public void setmaTT(String maTT) {
		this.maTT = maTT;
	}

	public String getTenTT() {
		return tenTT;
	}

	public void setTenTT(String tenTT) {
		this.tenTT = tenTT;
	}

	public List<TaiKhoanEntity> getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(List<TaiKhoanEntity> taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	
}
