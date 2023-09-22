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
@Table(name = "CTCC")
public class CT_CCEntity  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer iD;
	
	@ManyToOne
	@JoinColumn(name = "MANCC")
	private NhaCungCapEntity maNCC;
	
	@ManyToOne
	@JoinColumn(name = "MASVC")
	private SinhVatCanhEntity maSVC;

	public CT_CCEntity() {
		super();
	}

	public CT_CCEntity(Integer iD, NhaCungCapEntity maNCC, SinhVatCanhEntity maSVC) {
		super();
		this.iD = iD;
		this.maNCC = maNCC;
		this.maSVC = maSVC;
	}

	public Integer getiD() {
		return iD;
	}

	public void setiD(Integer iD) {
		this.iD = iD;
	}

	public NhaCungCapEntity getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(NhaCungCapEntity maNCC) {
		this.maNCC = maNCC;
	}

	public SinhVatCanhEntity getMaSVC() {
		return maSVC;
	}

	public void setMaSVC(SinhVatCanhEntity maSVC) {
		this.maSVC = maSVC;
	}

}