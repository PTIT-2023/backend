package aomanager.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class ThongTinModel {
	
	@NotBlank(message = "Email không được để trống!")
	@Email(message = "Email không hợp lệ")
	private String email;
	
	@NotBlank(message = "Mật khẩu không được để trống!")
	private String matKhau;
	
	@NotBlank(message = "Họ không được để trống!")
	private String ho;
	
	@NotBlank(message = "Tên không được để trống!")
	private String ten;
	
	private String gioiTinh;
	
	@NotBlank(message = "Địa chỉ không được để trống!")
	private String diaChi;
	
	@NotBlank(message = "Không được để trống!")
	private String sDT;
	
	private String maSoThue;
	
	public ThongTinModel() {
		super();
	}
	public ThongTinModel(String email, String matKhau, String ho, String ten, String gioiTinh, String diaChi,
			String sDT, String maSoThue) {
		super();
		this.email = email;
		this.matKhau = matKhau;
		this.ho = ho;
		this.ten = ten;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.sDT = sDT;
		this.maSoThue = maSoThue;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
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
}
