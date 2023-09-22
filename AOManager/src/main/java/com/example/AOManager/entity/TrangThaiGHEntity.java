package com.example.AOManager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TRANGTHAIGH")
public class TrangThaiGHEntity {

	@Id
	@Column(name = "MATT")
	private String maTT;
	
	@Column(name = "TENTT")
	private String tenTT;
	
	@OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maTT")
	private List<GioHangEntity> gioHang;

	public TrangThaiGHEntity() {
		super();
	}

	public TrangThaiGHEntity(String maTT, String tenTT, List<GioHangEntity> gioHang) {
		super();
		this.maTT = maTT;
		this.tenTT = tenTT;
		this.gioHang = gioHang;
	}
	
	public TrangThaiGHEntity(String maTT, String tenTT) {
		super();
		this.maTT = maTT;
		this.tenTT = tenTT;
	}

	public String getMaTT() {
		return maTT;
	}

	public void setMaTT(String maTT) {
		this.maTT = maTT;
	}

	public String getTenTT() {
		return tenTT;
	}

	public void setTenTT(String tenTT) {
		this.tenTT = tenTT;
	}

	public List<GioHangEntity> getGioHang() {
		return gioHang;
	}

	public void setGioHang(List<GioHangEntity> gioHang) {
		this.gioHang = gioHang;
	}
	
}
