package aomanager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LOAI")
public class LoaiEntity {
	
	@Id
	@Column(name = "MALOAI")
	private String maLoai;

	@Column(name = "TENLOAI")
	private String tenLoai;
	
	@OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maLoai")
	private List<SinhVatCanhEntity> sinhVatCanhList;

	public LoaiEntity() {
		super();
	}

	public LoaiEntity(String maLoai, String tenLoai, List<SinhVatCanhEntity> sinhVatCanhList) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
		this.sinhVatCanhList = sinhVatCanhList;
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public List<SinhVatCanhEntity> getSinhVatCanhList() {
		return sinhVatCanhList;
	}

	public void setSinhVatCanhList(List<SinhVatCanhEntity> sinhVatCanhList) {
		this.sinhVatCanhList = sinhVatCanhList;
	}

}
