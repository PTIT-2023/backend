package aomanager.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "GIOHANG")
public class GioHangEntity {
	
	@Id
    @Column(name = "MAGH")
	@NotBlank(message = "Mã không được để trống!")
    private String maGH;
	
    @Column(name = "NGAYDAT")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayDat;
    
    @Column(name = "TEN")
    @NotBlank(message = "Tên không được để trống!")
    private String ten;
    
    @Column(name = "DIACHINHAN")
    @NotBlank(message = "Địa chỉ nhận không được để trống!")
    private String diaChiNhan;
    
    @Column(name = "SDTNHAN")
    @NotBlank(message = "Số điện thoại không được để trống!")
    private String sDTNhan;
    
    @Column(name = "EMAILNHAN")
    private String emailNhan;
    
    @Column(name = "NGAYGIAO")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayGiao;
    
    @ManyToOne
    @JoinColumn(name = "MAHD")
    private HoaDonEntity maHD;
    
    @ManyToOne
	@JoinColumn(name = "MATT")
	private TrangThaiGHEntity maTT;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maGH")
    private List<CT_GHEntity> cT_GHList;
    
    @ManyToOne
    @JoinColumn(name = "MAKH")
    @NotNull
    private KhachHangEntity maKH;

	public GioHangEntity() {
		super();
	}

	public GioHangEntity(String maGH, Date ngayDat, String ten, String diaChiNhan, String sDTNhan, String emailNhan,
			Date ngayGiao, HoaDonEntity maHD, TrangThaiGHEntity maTT, List<CT_GHEntity> cT_GHList,
			KhachHangEntity maKH) {
		super();
		this.maGH = maGH;
		this.ngayDat = ngayDat;
		this.ten = ten;
		this.diaChiNhan = diaChiNhan;
		this.sDTNhan = sDTNhan;
		this.emailNhan = emailNhan;
		this.ngayGiao = ngayGiao;
		this.maHD = maHD;
		this.maTT = maTT;
		this.cT_GHList = cT_GHList;
		this.maKH = maKH;
	}

	public String getMaGH() {
		return maGH;
	}

	public void setMaGH(String maGH) {
		this.maGH = maGH;
	}

	public Date getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getDiaChiNhan() {
		return diaChiNhan;
	}

	public void setDiaChiNhan(String diaChiNhan) {
		this.diaChiNhan = diaChiNhan;
	}

	public String getsDTNhan() {
		return sDTNhan;
	}

	public void setsDTNhan(String sDTNhan) {
		this.sDTNhan = sDTNhan;
	}

	public String getEmailNhan() {
		return emailNhan;
	}

	public void setEmailNhan(String emailNhan) {
		this.emailNhan = emailNhan;
	}

	public Date getNgayGiao() {
		return ngayGiao;
	}

	public void setNgayGiao(Date ngayGiao) {
		this.ngayGiao = ngayGiao;
	}

	public HoaDonEntity getMaHD() {
		return maHD;
	}

	public void setMaHD(HoaDonEntity maHD) {
		this.maHD = maHD;
	}

	public TrangThaiGHEntity getMaTT() {
		return maTT;
	}

	public void setMaTT(TrangThaiGHEntity maTT) {
		this.maTT = maTT;
	}

	public List<CT_GHEntity> getcT_GHList() {
		return cT_GHList;
	}

	public void setcT_GHList(List<CT_GHEntity> cT_GHList) {
		this.cT_GHList = cT_GHList;
	}

	public KhachHangEntity getMaKH() {
		return maKH;
	}

	public void setMaKH(KhachHangEntity maKH) {
		this.maKH = maKH;
	}

}
