package com.example.AOManager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "HINHANH")
public class HinhAnhEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDHA")
    private Integer iDHA;
	
    @Column(name = "DUONGDAN")
    @NotBlank(message = "Đường dẫn không được bỏ trống!")
    private String duongDan;
    
    @ManyToOne
    @JoinColumn(name = "MASVC")
    private SinhVatCanhEntity maSVC;

	public HinhAnhEntity() {
		super();
	}

	public HinhAnhEntity(Integer iDHA, String duongDan, SinhVatCanhEntity maSVC) {
		super();
		this.iDHA = iDHA;
		this.duongDan = duongDan;
		this.maSVC = maSVC;
	}

	public Integer getIDHA() {
		return iDHA;
	}

	public void setMaHA(Integer maHA) {
		this.iDHA = maHA;
	}

	public String getDuongDan() {
		return duongDan;
	}

	public void setDuongDan(String duongDan) {
		this.duongDan = duongDan;
	}

	public SinhVatCanhEntity getMaSVC() {
		return maSVC;
	}

	public void setMaSVC(SinhVatCanhEntity maSVC) {
		this.maSVC = maSVC;
	}
	
}
