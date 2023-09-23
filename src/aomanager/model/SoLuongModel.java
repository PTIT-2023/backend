package aomanager.model;

public class SoLuongModel {
	
	String maSVC;
	int soLuongCu;
	int soLuongMoi;

	public SoLuongModel() {
		super();
	}

	public SoLuongModel(String maSVC, int soLuongCu, int soLuongMoi) {
		super();
		this.maSVC = maSVC;
		this.soLuongCu = soLuongCu;
		this.soLuongMoi = soLuongMoi;
	}

	public String getMaSVC() {
		return maSVC;
	}

	public void setMaSVC(String maSVC) {
		this.maSVC = maSVC;
	}

	public int getSoLuongCu() {
		return soLuongCu;
	}

	public void setSoLuongCu(int soLuongCu) {
		this.soLuongCu = soLuongCu;
	}

	public int getSoLuongMoi() {
		return soLuongMoi;
	}

	public void setSoLuongMoi(int soLuongMoi) {
		this.soLuongMoi = soLuongMoi;
	}

}
