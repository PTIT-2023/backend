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

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "SINHVATCANH")
public class SinhVatCanhEntity {

	@Id
	@Column(name = "MASVC")
	@NotBlank(message = "Mã không được để trống!")
	private String maSVC;

	@Column(name = "TEN")
	@NotBlank(message = "Tên không được để trống!")
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

	public SinhVatCanhEntity() {
		super();
	}

	public SinhVatCanhEntity(String maSVC, String ten, int gia, String moTa, String moiTruongSong,
			String nhietDoMoiTruong, String doPH, String viTri, String hinhThucSinhSan, String loaiThucAn,
			String kichThuocToiDa, Integer soLuongTon, Integer luotMua, LoaiEntity maLoai, TrangThaiSPEntity maTT,
			List<CT_KMEntity> cT_KMList, List<CT_GHEntity> cT_GHList, List<CT_CCEntity> cT_CCList,
			List<CT_PNEntity> cT_PNList, List<ThayDoiGiaEntity> thayDoiGiaList, List<CT_PDEntity> cT_PDList,
			List<HinhAnhEntity> hinhAnhList) {
		super();
		this.maSVC = maSVC;
		this.ten = ten;
		this.gia = gia;
		this.moTa = moTa;
		this.moiTruongSong = moiTruongSong;
		this.nhietDoMoiTruong = nhietDoMoiTruong;
		this.doPH = doPH;
		this.viTri = viTri;
		this.hinhThucSinhSan = hinhThucSinhSan;
		this.loaiThucAn = loaiThucAn;
		this.kichThuocToiDa = kichThuocToiDa;
		this.soLuongTon = soLuongTon;
		this.luotMua = luotMua;
		this.maLoai = maLoai;
		this.maTT = maTT;
		this.cT_KMList = cT_KMList;
		this.cT_GHList = cT_GHList;
		this.cT_CCList = cT_CCList;
		this.cT_PNList = cT_PNList;
		this.thayDoiGiaList = thayDoiGiaList;
		this.cT_PDList = cT_PDList;
		this.hinhAnhList = hinhAnhList;
	}

	public String getMaSVC() {
		return maSVC;
	}

	public void setMaSVC(String maSVC) {
		this.maSVC = maSVC;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getMoiTruongSong() {
		return moiTruongSong;
	}

	public void setMoiTruongSong(String moiTruongSong) {
		this.moiTruongSong = moiTruongSong;
	}

	public String getNhietDoMoiTruong() {
		return nhietDoMoiTruong;
	}

	public void setNhietDoMoiTruong(String nhietDoMoiTruong) {
		this.nhietDoMoiTruong = nhietDoMoiTruong;
	}

	public String getDoPH() {
		return doPH;
	}

	public void setDoPH(String doPH) {
		this.doPH = doPH;
	}

	public String getViTri() {
		return viTri;
	}

	public void setViTri(String viTri) {
		this.viTri = viTri;
	}

	public String getHinhThucSinhSan() {
		return hinhThucSinhSan;
	}

	public void setHinhThucSinhSan(String hinhThucSinhSan) {
		this.hinhThucSinhSan = hinhThucSinhSan;
	}

	public String getLoaiThucAn() {
		return loaiThucAn;
	}

	public void setLoaiThucAn(String loaiThucAn) {
		this.loaiThucAn = loaiThucAn;
	}

	public String getKichThuocToiDa() {
		return kichThuocToiDa;
	}

	public void setKichThuocToiDa(String kichThuocToiDa) {
		this.kichThuocToiDa = kichThuocToiDa;
	}

	public Integer getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(Integer soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public Integer getLuotMua() {
		return luotMua;
	}

	public void setLuotMua(Integer luotMua) {
		this.luotMua = luotMua;
	}

	public LoaiEntity getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(LoaiEntity maLoai) {
		this.maLoai = maLoai;
	}

	public TrangThaiSPEntity getMaTT() {
		return maTT;
	}

	public void setMaTT(TrangThaiSPEntity maTT) {
		this.maTT = maTT;
	}

	public List<CT_KMEntity> getcT_KMList() {
		return cT_KMList;
	}

	public void setcT_KMList(List<CT_KMEntity> cT_KMList) {
		this.cT_KMList = cT_KMList;
	}

	public List<CT_GHEntity> getcT_GHList() {
		return cT_GHList;
	}

	public void setcT_GHList(List<CT_GHEntity> cT_GHList) {
		this.cT_GHList = cT_GHList;
	}

	public List<CT_CCEntity> getcT_CCList() {
		return cT_CCList;
	}

	public void setcT_CCList(List<CT_CCEntity> cT_CCList) {
		this.cT_CCList = cT_CCList;
	}

	public List<CT_PNEntity> getcT_PNList() {
		return cT_PNList;
	}

	public void setcT_PNList(List<CT_PNEntity> cT_PNList) {
		this.cT_PNList = cT_PNList;
	}

	public List<ThayDoiGiaEntity> getThayDoiGiaList() {
		return thayDoiGiaList;
	}

	public void setThayDoiGiaList(List<ThayDoiGiaEntity> thayDoiGiaList) {
		this.thayDoiGiaList = thayDoiGiaList;
	}

	public List<CT_PDEntity> getcT_PDList() {
		return cT_PDList;
	}

	public void setcT_PDList(List<CT_PDEntity> cT_PDList) {
		this.cT_PDList = cT_PDList;
	}

	public List<HinhAnhEntity> getHinhAnhList() {
		return hinhAnhList;
	}

	public void setHinhAnhList(List<HinhAnhEntity> hinhAnhList) {
		this.hinhAnhList = hinhAnhList;
	}
	
	public String getGiaVN() {
		// TODO Auto-generated method stub
		DecimalFormat formatter = new DecimalFormat("###,###,###");

		return formatter.format(gia)+" VNĐ";

	}
}
