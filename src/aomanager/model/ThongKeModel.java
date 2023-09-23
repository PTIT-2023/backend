package aomanager.model;

import java.util.Date;

public class ThongKeModel {

	private Date ngayBD;
	private Date ngayKT;

	public ThongKeModel() {
		super();
	}

	public ThongKeModel(Date ngayBD, Date ngayKT) {
		super();
		this.ngayBD = ngayBD;
		this.ngayKT = ngayKT;
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

}
