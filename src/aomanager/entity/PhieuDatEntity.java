package aomanager.entity;

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
@Table(name = "PHIEUDAT")
public class PhieuDatEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAPD")
    private Integer maPD;
	
    @Column(name = "NGAYDAT")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayDat;
    
    @Column(name = "MAPN")
    private String maPN;
    
    @OneToOne(cascade = CascadeType.REFRESH, mappedBy = "maPD")
    private PhieuNhapEntity phieuNhap;
    
    @ManyToOne
    @JoinColumn(name = "MANCC")
    private NhaCungCapEntity maNCC;
    
    @ManyToOne
    @JoinColumn(name = "MANV")
    private NhanVienEntity maNV;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maPD")
    private List<CT_PDEntity> cT_PDList;

	public PhieuDatEntity() {
		super();
	}

	public PhieuDatEntity(Integer maPD, Date ngayDat, String maPN, PhieuNhapEntity phieuNhap, NhaCungCapEntity maNCC,
			NhanVienEntity maNV, List<CT_PDEntity> cT_PDList) {
		super();
		this.maPD = maPD;
		this.ngayDat = ngayDat;
		this.maPN = maPN;
		this.phieuNhap = phieuNhap;
		this.maNCC = maNCC;
		this.maNV = maNV;
		this.cT_PDList = cT_PDList;
	}

	public Integer getMaPD() {
		return maPD;
	}

	public void setMaPD(Integer maPD) {
		this.maPD = maPD;
	}

	public Date getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}

	public String getMaPN() {
		return maPN;
	}

	public void setMaPN(String maPN) {
		this.maPN = maPN;
	}

	public PhieuNhapEntity getPhieuNhap() {
		return phieuNhap;
	}

	public void setPhieuNhap(PhieuNhapEntity phieuNhap) {
		this.phieuNhap = phieuNhap;
	}

	public NhaCungCapEntity getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(NhaCungCapEntity maNCC) {
		this.maNCC = maNCC;
	}

	public NhanVienEntity getMaNV() {
		return maNV;
	}

	public void setMaNV(NhanVienEntity maNV) {
		this.maNV = maNV;
	}

	public List<CT_PDEntity> getcT_PDList() {
		return cT_PDList;
	}

	public void setcT_PDList(List<CT_PDEntity> cT_PDList) {
		this.cT_PDList = cT_PDList;
	}
    
}
