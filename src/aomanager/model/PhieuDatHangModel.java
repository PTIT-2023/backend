package aomanager.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class PhieuDatHangModel {
	
	@NotBlank(message = "Tên không được để trống!")
	private String ten;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ngayDat;
	@NotBlank(message = "Địa chỉ nhận không được để trống!")
	private String diaChiNhan;
	@NotBlank(message = "Số điện thoại không được để trống!")
	private String sDTNhan;
	private String emailNhan;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ngayGiao;
	
	public PhieuDatHangModel() {
		super();
	}

	public PhieuDatHangModel(String ten, Date ngayDat, String diaChiNhan, String sDTNhan, String emailNhan,
			Date ngayGiao) {
		super();
		this.ten = ten;
		this.ngayDat = ngayDat;
		this.diaChiNhan = diaChiNhan;
		this.sDTNhan = sDTNhan;
		this.emailNhan = emailNhan;
		this.ngayGiao = ngayGiao;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public Date getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
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
	
}
