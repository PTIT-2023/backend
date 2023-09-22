package com.example.AOManager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CTKM")
public class CT_KMEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer iD;
	
    @Column(name = "PHANTRAMGIAM")
    private double phanTramGiam;
    
    @ManyToOne
    @JoinColumn(name = "MAKM")
    private KhuyenMaiEntity maKM;
    
    @ManyToOne
    @JoinColumn(name = "MASVC")
    private SinhVatCanhEntity maSVC;

	public CT_KMEntity() {
		super();
	}

	public CT_KMEntity(Integer iD, double phanTramGiam, KhuyenMaiEntity maKM, SinhVatCanhEntity maSVC) {
		super();
		this.iD = iD;
		this.phanTramGiam = phanTramGiam;
		this.maKM = maKM;
		this.maSVC = maSVC;
	}

	public Integer getiD() {
		return iD;
	}

	public void setiD(Integer iD) {
		this.iD = iD;
	}

	public double getPhanTramGiam() {
		return phanTramGiam;
	}

	public void setPhanTramGiam(double phanTramGiam) {
		this.phanTramGiam = phanTramGiam;
	}

	public KhuyenMaiEntity getMaKM() {
		return maKM;
	}

	public void setMaKM(KhuyenMaiEntity maKM) {
		this.maKM = maKM;
	}

	public SinhVatCanhEntity getMaSVC() {
		return maSVC;
	}

	public void setMaSVC(SinhVatCanhEntity maSVC) {
		this.maSVC = maSVC;
	}
	
}
