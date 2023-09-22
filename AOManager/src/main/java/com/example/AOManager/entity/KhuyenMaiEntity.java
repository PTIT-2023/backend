package com.example.AOManager.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "KHUYENMAI")
public class KhuyenMaiEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAKM")
    private Integer maKM;
	
    @Column(name = "MOTA")
    private String moTa;
    
    @Column(name = "NGAYBD")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayBD;
    
    @Column(name = "NGAYKT")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayKT;
    
    @ManyToOne
    @JoinColumn(name = "MANV")
    private NhanVienEntity maNV;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maKM")
    private List<CT_KMEntity> cT_KMList;

	public KhuyenMaiEntity() {
		super();
	}

	public KhuyenMaiEntity(Integer maKM, String moTa, Date ngayBD, Date ngayKT, NhanVienEntity maNV,
			List<CT_KMEntity> cT_KMList) {
		super();
		this.maKM = maKM;
		this.moTa = moTa;
		this.ngayBD = ngayBD;
		this.ngayKT = ngayKT;
		this.maNV = maNV;
		this.cT_KMList = cT_KMList;
	}

	public Integer getMaKM() {
		return maKM;
	}

	public void setMaKM(Integer maKM) {
		this.maKM = maKM;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public Date getNgayBD() {
		return ngayBD;
	}

	public void setNgayBD(Date ngayBD) {
		this.ngayBD = ngayBD;
	}

	public Date getNgayKT() {
		return ngayKT;
	}

	public void setNgayKT(Date ngayKT) {
		this.ngayKT = ngayKT;
	}

	public NhanVienEntity getMaNV() {
		return maNV;
	}

	public void setMaNV(NhanVienEntity maNV) {
		this.maNV = maNV;
	}

	public List<CT_KMEntity> getcT_KMList() {
		return cT_KMList;
	}

	public void setcT_KMList(List<CT_KMEntity> cT_KMList) {
		this.cT_KMList = cT_KMList;
	}
    
}
