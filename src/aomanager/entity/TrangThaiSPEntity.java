package aomanager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TRANGTHAISP")
public class TrangThaiSPEntity {
	
	@Id
	@Column(name = "MATT")
	private String maTT;
	
	@Column(name = "TENTT")
	private String tenTT;
	
	@OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maTT")
	private List<SinhVatCanhEntity> sinhVatCanh;

	public TrangThaiSPEntity() {
		super();
	}

	public TrangThaiSPEntity(String maTT, String tenTT, List<SinhVatCanhEntity> sinhVatCanh) {
		super();
		this.maTT = maTT;
		this.tenTT = tenTT;
		this.sinhVatCanh = sinhVatCanh;
	}

	public String getmaTT() {
		return maTT;
	}

	public void setmaTT(String maTT) {
		this.maTT = maTT;
	}

	public String getTenTT() {
		return tenTT;
	}

	public void setTenTT(String tenTT) {
		this.tenTT = tenTT;
	}

	public List<SinhVatCanhEntity> getSinhVatCanh() {
		return sinhVatCanh;
	}

	public void setSinhVatCanh(List<SinhVatCanhEntity> sinhVatCanh) {
		this.sinhVatCanh = sinhVatCanh;
	}
	
}
