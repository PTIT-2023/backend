package com.example.AOManager.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PHIEUNHAP")
public class PhieuNhapEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAPN")
    private Integer maPN;
	
    @Column(name = "NGAYTAO")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayTao;
    
    @ManyToOne   
    @JoinColumn(name = "MANV")
    private NhanVienEntity maNV;
    
    @OneToOne
    @JoinColumn(name = "MAPD")
    private PhieuDatEntity maPD;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maPN")
    private List<CT_PNEntity> cT_PNList;

	public PhieuNhapEntity() {
		super();
	}

	public PhieuNhapEntity(Integer maPN, Date ngayTao, NhanVienEntity maNV, PhieuDatEntity maPD,
			List<CT_PNEntity> cT_PNList) {
		super();
		this.maPN = maPN;
		this.ngayTao = ngayTao;
		this.maNV = maNV;
		this.maPD = maPD;
		this.cT_PNList = cT_PNList;
	}

	public Integer getMaPN() {
		return maPN;
	}

	public void setMaPN(Integer maPN) {
		this.maPN = maPN;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}

	public NhanVienEntity getMaNV() {
		return maNV;
	}

	public void setMaNV(NhanVienEntity maNV) {
		this.maNV = maNV;
	}

	public PhieuDatEntity getMaPD() {
		return maPD;
	}

	public void setMaPD(PhieuDatEntity maPD) {
		this.maPD = maPD;
	}

	public List<CT_PNEntity> getcT_PNList() {
		return cT_PNList;
	}

	public void setcT_PNList(List<CT_PNEntity> cT_PNList) {
		this.cT_PNList = cT_PNList;
	}

}
