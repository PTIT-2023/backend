package com.example.AOManager.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TAIKHOAN")
public class TaiKhoanEntity {

	@Id
    @Column(name = "MATK")
    private String maTK;
	
	@Column(name = "EMAIL")
    private String email;
    
    @Column(name = "MATKHAU")
    private String matKhau;
    
    @ManyToOne
    @JoinColumn(name = "MAQUYEN")
    private QuyenEntity maQuyen;
    
    @ManyToOne
	@JoinColumn(name = "MATT")
	private TrangThaiTKEntity maTT;
    
    @OneToOne(cascade = CascadeType.REFRESH, mappedBy = "maTK")
    private KhachHangEntity khachHang;
    
    @OneToOne(cascade = CascadeType.REFRESH, mappedBy = "maTK")
    private NhanVienEntity nhanVien;
	
	public TaiKhoanEntity(String maTK, String email, String matKhau, QuyenEntity maQuyen, TrangThaiTKEntity maTT) {

		super();
		this.maTK = maTK;
		this.email = email;
		this.matKhau = matKhau;
		this.maQuyen = maQuyen;
		this.maTT = maTT;
	}

}
