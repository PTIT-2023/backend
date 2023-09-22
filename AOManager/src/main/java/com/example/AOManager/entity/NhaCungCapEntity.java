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
@Table(name = "NHACUNGCAP")
public class NhaCungCapEntity {
	
    @Id
    @Column(name = "MANCC")
    private String maNCC;
    
    @Column(name = "TENNCC") 
    private String tenNCC;
    
    @Column(name = "DIACHI")
    private String diaChi;
    
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "SDT")
    private String sDT;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maNCC")
    private List<CT_CCEntity> cT_CCList;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maNCC")
    private List<PhieuDatEntity> phieuDatList;

	public NhaCungCapEntity() {
		super();
	}

	public NhaCungCapEntity(String maNCC, String tenNCC, String diaChi, String email, String sDT,
			List<CT_CCEntity> cT_CCList, List<PhieuDatEntity> phieuDatList) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.diaChi = diaChi;
		this.email = email;
		this.sDT = sDT;
		this.cT_CCList = cT_CCList;
		this.phieuDatList = phieuDatList;
	}

	public String getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}

	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getsDT() {
		return sDT;
	}

	public void setsDT(String sDT) {
		this.sDT = sDT;
	}

	public List<CT_CCEntity> getcT_CCList() {
		return cT_CCList;
	}

	public void setcT_CCList(List<CT_CCEntity> cT_CCList) {
		this.cT_CCList = cT_CCList;
	}

	public List<PhieuDatEntity> getPhieuDatList() {
		return phieuDatList;
	}

	public void setPhieuDatList(List<PhieuDatEntity> phieuDatList) {
		this.phieuDatList = phieuDatList;
	}
    
}
