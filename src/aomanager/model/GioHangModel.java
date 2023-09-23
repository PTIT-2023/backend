package aomanager.model;

import java.text.DecimalFormat;

public class GioHangModel {
	private String maSVC;
	private int donGia;
	private int gia;
	private int soLuong;
	private String ten;
	private String duongDan;
	
	public GioHangModel() {
		super();
	}

	public GioHangModel(String maSVC, int gia, int soLuong, String ten, String duongDan) {
		super();
		this.maSVC = maSVC;
		this.gia = gia;
		this.soLuong = soLuong;
		this.ten = ten;
		this.duongDan = duongDan;
	}

	public String getMaSVC() {
		return maSVC;
	}

	public void setMaSVC(String maSVC) {
		this.maSVC = maSVC;
	}

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getDuongDan() {
		return duongDan;
	}

	public void setDuongDan(String duongDan) {
		this.duongDan = duongDan;
	}
	
	public String getGiaVN() {
		// TODO Auto-generated method stub
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		return formatter.format(gia)+" VNĐ";
	}
	
	public String getDonGia() {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		int donGia = gia/soLuong;
		return formatter.format(donGia)+" VNĐ";
	}
}
