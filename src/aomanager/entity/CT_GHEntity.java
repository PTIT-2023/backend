package aomanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CTGH")
public class CT_GHEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer iD;
	
    @Column(name = "SOLUONG")
    private int soLuong;
    
    @Column(name = "GIA")
    private double gia;
    
    @ManyToOne
    @JoinColumn(name = "MAGH")
    private GioHangEntity maGH;
    
    @ManyToOne
    @JoinColumn(name = "MASVC")
    private SinhVatCanhEntity maSVC;

	public CT_GHEntity() {
		super();
	}

	public CT_GHEntity(Integer iD, int soLuong, double gia, GioHangEntity maGH, SinhVatCanhEntity maSVC) {
		super();
		this.iD = iD;
		this.soLuong = soLuong;
		this.gia = gia;
		this.maGH = maGH;
		this.maSVC = maSVC;
	}

	public Integer getiD() {
		return iD;
	}

	public void setiD(Integer iD) {
		this.iD = iD;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getGia() {
		return gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	public GioHangEntity getMaGH() {
		return maGH;
	}

	public void setMaGH(GioHangEntity maGH) {
		this.maGH = maGH;
	}

	public SinhVatCanhEntity getMaSVC() {
		return maSVC;
	}

	public void setMaSVC(SinhVatCanhEntity maSVC) {
		this.maSVC = maSVC;
	}
	
}
