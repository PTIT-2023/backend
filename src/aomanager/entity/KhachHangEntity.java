package aomanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "KHACHHANG")
public class KhachHangEntity {

	@Id
	@Column(name = "MAKH")
	@NotBlank(message = "Mã khách hàng không được bỏ trống!")
	private String maKH;

	@Column(name = "HO")
	@NotBlank(message = "Họ không được bỏ trống!")
	private String ho;

	@Column(name = "TEN")
	@NotBlank(message = "Tên không được bỏ trống!")
	private String ten;

	@Column(name = "GIOITINH")
	private String gioiTinh;

	@Column(name = "DIACHI")
	@NotBlank(message = "Địa chỉ không được bỏ trống!")
	private String diaChi;

	@Column(name = "SDT")
	@NotBlank(message = "Số điện thoại không được bỏ trống!")
	@Pattern(regexp = "0[0-9]{9,10}", message = "Số điện thoại không hợp lệ!")
	private String sDT;

	@Column(name = "MASOTHUE")
	private String maSoThue;

	@OneToOne
	@JoinColumn(name = "MATK")
	private TaiKhoanEntity maTK;

	public KhachHangEntity() {
		super();
	}

	public KhachHangEntity(String maKH, String ho, String ten, String gioiTinh, String diaChi, String sDT,
			String maSoThue, TaiKhoanEntity maTK) {
		super();
		this.maKH = maKH;
		this.ho = ho;
		this.ten = ten;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.sDT = sDT;
		this.maSoThue = maSoThue;
		this.maTK = maTK;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getsDT() {
		return sDT;
	}

	public void setsDT(String sDT) {
		this.sDT = sDT;
	}

	public String getMaSoThue() {
		return maSoThue;
	}

	public void setMaSoThue(String maSoThue) {
		this.maSoThue = maSoThue;
	}

	public TaiKhoanEntity getMaTK() {
		return maTK;
	}

	public void setMaTK(TaiKhoanEntity maTK) {
		this.maTK = maTK;
	}

}
