package aomanager.model;

import java.text.DecimalFormat;
import java.util.Date;

public class HoaDonModel {

	private Date ngayTao;
	private int tongTien;
	private String ngayTaoTN;

	public HoaDonModel() {
		super();
	}

	public HoaDonModel(Date ngayTao, int tongTien) {
		super();
		this.ngayTao = ngayTao;
		this.tongTien = tongTien;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}

	public int getTongTien() {
		return tongTien;
	}

	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}

	public String getNgayTaoTN() {
		return ngayTaoTN;
	}

	public void setNgayTaoTN(String ngayTaoTN) {
		this.ngayTaoTN = ngayTaoTN;
	}

	public String getGiaVN() {
		// TODO Auto-generated method stub
		DecimalFormat formatter = new DecimalFormat("###,###,###");

		return formatter.format(tongTien)+" VNĐ";

	}
}
