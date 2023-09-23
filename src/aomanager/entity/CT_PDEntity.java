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
@Table(name = "CTPD")
public class CT_PDEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer iD;
	
    @Column(name = "SOLUONG")
    private int soLuong;
    
    @Column(name = "GIA")
    private int gia;
    
    @ManyToOne
    @JoinColumn(name = "MAPD")
    private PhieuDatEntity maPD;
    
    @ManyToOne
    @JoinColumn(name = "MASVC")
    private SinhVatCanhEntity maSVC;

	public CT_PDEntity() {
		super();
	}

	public CT_PDEntity(Integer iD, int soLuong, int gia, PhieuDatEntity maPD, SinhVatCanhEntity maSVC) {
		super();
		this.iD = iD;
		this.soLuong = soLuong;
		this.gia = gia;
		this.maPD = maPD;
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

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}

	public PhieuDatEntity getMaPD() {
		return maPD;
	}

	public void setMaPD(PhieuDatEntity maPD) {
		this.maPD = maPD;
	}

	public SinhVatCanhEntity getMaSVC() {
		return maSVC;
	}

	public void setMaSVC(SinhVatCanhEntity maSVC) {
		this.maSVC = maSVC;
	}
	
}
