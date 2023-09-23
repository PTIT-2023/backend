package aomanager.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "HOADON")
public class HoaDonEntity {

	@Id
	@Column(name = "MAHD")
	private String maHD;

	@Column(name = "NGAYTAO")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ngayTao;

	@Column(name = "TONGTIEN")
	private Integer tongTien;

	@Column(name = "MASOTHUE")
	private String maSoThue;

	@OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maHD")
    private List<GioHangEntity> gHList;

	@ManyToOne
	@JoinColumn(name = "MANV")
	private NhanVienEntity maNV;

	public HoaDonEntity() {
		super();
	}

	public HoaDonEntity(String maHD, Date ngayTao, Integer tongTien, String maSoThue,
			List<GioHangEntity> gHList, NhanVienEntity maNV) {
		super();
		this.maHD = maHD;
		this.ngayTao = ngayTao;
		this.tongTien = tongTien;
		this.maSoThue = maSoThue;
		this.gHList = gHList;
		this.maNV = maNV;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}

	public Integer getTongTien() {
		return tongTien;
	}

	public void setTongTien(Integer tongTien) {
		this.tongTien = tongTien;
	}

	public String getMaSoThue() {
		return maSoThue;
	}

	public void setMaSoThue(String maSoThue) {
		this.maSoThue = maSoThue;
	}
	
	public List<GioHangEntity> getgHList() {
		return gHList;
	}

	public void setgHList(List<GioHangEntity> gHList) {
		this.gHList = gHList;
	}

	public NhanVienEntity getMaNV() {
		return maNV;
	}

	public void setMaNV(NhanVienEntity maNV) {
		this.maNV = maNV;
	}

}
