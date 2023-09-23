package com.example.AOManager.entity;

import java.text.DecimalFormat;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SINHVATCANH")
public class SinhVatCanhEntity {

	@Id
	@Column(name = "MASVC")
	private String maSVC;

	@Column(name = "TEN")
	private String ten;

	@Column(name = "GIA")
	private int gia;

	@Column(name = "MOTA")
	private String moTa;

	@Column(name = "MOITRUONGSONG")
	private String moiTruongSong;

	@Column(name = "NHIETDOMOITRUONG")
	private String nhietDoMoiTruong;

	@Column(name = "DOPH")
	private String doPH;

	@Column(name = "VITRI")
	private String viTri;

	@Column(name = "HINHTHUCSINHSAN")
	private String hinhThucSinhSan;

	@Column(name = "LOAITHUCAN")
	private String loaiThucAn;

	@Column(name = "KICHTHUOCTOIDA")
	private String kichThuocToiDa;
	
	@Column(name = "SOLUONGTON")
	private Integer soLuongTon;
	
	@Column(name = "LUOTMUA")
	private Integer luotMua;
	
	@ManyToOne
	@JoinColumn(name = "MALOAI")
	private LoaiEntity maLoai;
	
	@ManyToOne
	@JoinColumn(name = "MATT")
	private TrangThaiSPEntity maTT;

	@OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maSVC")
	private List<CT_KMEntity> cT_KMList;

	@OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maSVC")
	private List<CT_GHEntity> cT_GHList;

	@OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maSVC")
	private List<CT_CCEntity> cT_CCList;

	@OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maSVC")
	private List<CT_PNEntity> cT_PNList;

	@OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maSVC")
	private List<ThayDoiGiaEntity> thayDoiGiaList;

	@OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maSVC")
	private List<CT_PDEntity> cT_PDList;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "maSVC")
	private List<HinhAnhEntity> hinhAnhList;
	
	public String getGiaVN() {
		// TODO Auto-generated method stub
		DecimalFormat formatter = new DecimalFormat("###,###,###");

		return formatter.format(gia)+" VNĐ";

	}
}
