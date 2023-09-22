package com.example.AOManager.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "QUYEN")
public class QuyenEntity {
	
	@Id
    @Column(name = "MAQUYEN")
    private String maQuyen;
	
    @Column(name = "TENQUYEN")
    private String tenQuyen;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maQuyen")
    private List<TaiKhoanEntity> taiKhoanList;

	public QuyenEntity() {
		super();
	}
	
	public QuyenEntity(String maQuyen, String tenQuyen) {
		super();
		this.maQuyen = maQuyen;
		this.tenQuyen = tenQuyen;
	}

	public QuyenEntity(String maQuyen, String tenQuyen, List<TaiKhoanEntity> taiKhoanList) {
		super();
		this.maQuyen = maQuyen;
		this.tenQuyen = tenQuyen;
		this.taiKhoanList = taiKhoanList;
	}

	public String getMaQuyen() {
		return maQuyen;
	}

	public void setMaQuyen(String maQuyen) {
		this.maQuyen = maQuyen;
	}

	public String getTenQuyen() {
		return tenQuyen;
	}

	public void setTenQuyen(String tenQuyen) {
		this.tenQuyen = tenQuyen;
	}

	public List<TaiKhoanEntity> getTaiKhoanList() {
		return taiKhoanList;
	}

	public void setTaiKhoanList(List<TaiKhoanEntity> taiKhoanList) {
		this.taiKhoanList = taiKhoanList;
	}
 
}
