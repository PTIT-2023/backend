package aomanager.entity;


import java.text.DecimalFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "THAYDOIGIA")
public class ThayDoiGiaEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer iD;
	
    @Column(name = "NGAYAPDUNG")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Ngày áp dụng không được bỏ trống!")
    private Date ngayApDung;
    
    @Column(name = "GIA")
    @NotNull(message = "Giá không được bỏ trống!")
    @Min(value = 1000, message = "Giá không được nhỏ hơn 1000VND!")
    private int gia;
    
    @ManyToOne
    @JoinColumn(name = "MANV")
    private NhanVienEntity maNV;
    
    @ManyToOne
    @JoinColumn(name = "MASVC")
    @NotNull(message = "Sinh vật cảnh không được bỏ trống!")
    private SinhVatCanhEntity maSVC;

	public ThayDoiGiaEntity() {
		super();
	}

	public ThayDoiGiaEntity(Integer iD, Date ngayApDung, int gia, NhanVienEntity maNV, SinhVatCanhEntity maSVC) {
		super();
		this.iD = iD;
		this.ngayApDung = ngayApDung;
		this.gia = gia;
		this.maNV = maNV;
		this.maSVC = maSVC;
	}

	public Integer getiD() {
		return iD;
	}

	public void setiD(Integer iD) {
		this.iD = iD;
	}

	public Date getNgayApDung() {
		return ngayApDung;
	}

	public void setNgayApDung(Date ngayApDung) {
		this.ngayApDung = ngayApDung;
	}

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}

	public NhanVienEntity getMaNV() {
		return maNV;
	}

	public void setMaNV(NhanVienEntity maNV) {
		this.maNV = maNV;
	}

	public SinhVatCanhEntity getMaSVC() {
		return maSVC;
	}

	public void setMaSVC(SinhVatCanhEntity maSVC) {
		this.maSVC = maSVC;
	}
	
	public String getGiaVN() {
		// TODO Auto-generated method stub
		DecimalFormat formatter = new DecimalFormat("###,###,###");

		return formatter.format(gia)+" VNĐ";

	}
	
}
