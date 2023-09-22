package com.example.AOManager.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "NHANVIEN")
public class NhanVienEntity {

	@Id
    @Column(name = "maNV")
	@NotBlank(message = "Mã nhân viên không được bỏ trống!")
    private String maNV;
	
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
    @Pattern(regexp = "0[0-9]{9,10}", message = "Số điện thoại không hợp lệ!")
    @NotBlank(message = "Số điện thoại không được bỏ trống!")
    private String sDT;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maNV")
    private List<PhieuNhapEntity> phieuNhapList;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maNV")
    private List<KhuyenMaiEntity> khuyenMaiList;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maNV")
    private List<PhieuDatEntity> phieuDatList;
  
    @OneToOne
    @JoinColumn(name = "MATK")
    private TaiKhoanEntity maTK;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maNV")
    private List<ThayDoiGiaEntity> thayDoiGiaList;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maNV")
    private List<HoaDonEntity> hoaDonList;

	public NhanVienEntity() {
		super();
	}

	public NhanVienEntity(String maNV, String ho, String ten, String gioiTinh, String diaChi, String sDT, List<PhieuNhapEntity> phieuNhapList, List<KhuyenMaiEntity> khuyenMaiList,
			List<PhieuDatEntity> phieuDatList, TaiKhoanEntity maTK,
			List<ThayDoiGiaEntity> thayDoiGiaList, List<HoaDonEntity> hoaDonList) {
		super();
		this.maNV = maNV;
		this.ho = ho;
		this.ten = ten;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.sDT = sDT;
		this.phieuNhapList = phieuNhapList;
		this.khuyenMaiList = khuyenMaiList;
		this.phieuDatList = phieuDatList;
		this.maTK = maTK;
		this.thayDoiGiaList = thayDoiGiaList;
		this.hoaDonList = hoaDonList;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
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

	public List<PhieuNhapEntity> getPhieuNhapList() {
		return phieuNhapList;
	}

	public void setPhieuNhapList(List<PhieuNhapEntity> phieuNhapList) {
		this.phieuNhapList = phieuNhapList;
	}

	public List<KhuyenMaiEntity> getKhuyenMaiList() {
		return khuyenMaiList;
	}

	public void setKhuyenMaiList(List<KhuyenMaiEntity> khuyenMaiList) {
		this.khuyenMaiList = khuyenMaiList;
	}

	public List<PhieuDatEntity> getPhieuDatList() {
		return phieuDatList;
	}

	public void setPhieuDatList(List<PhieuDatEntity> phieuDatList) {
		this.phieuDatList = phieuDatList;
	}

	public TaiKhoanEntity getMaTK() {
		return maTK;
	}

	public void setMaTK(TaiKhoanEntity maTK) {
		this.maTK = maTK;
	}

	public List<ThayDoiGiaEntity> getThayDoiGiaList() {
		return thayDoiGiaList;
	}

	public void setThayDoiGiaList(List<ThayDoiGiaEntity> thayDoiGiaList) {
		this.thayDoiGiaList = thayDoiGiaList;
	}

	public List<HoaDonEntity> getHoaDonList() {
		return hoaDonList;
	}

	public void setHoaDonList(List<HoaDonEntity> hoaDonList) {
		this.hoaDonList = hoaDonList;
	}

}
