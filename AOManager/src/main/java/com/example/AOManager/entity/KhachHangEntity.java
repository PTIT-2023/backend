package com.example.AOManager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

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
@Table(name = "KHACHHANG")
public class KhachHangEntity {

	@Id
	@Column(name = "MAKH")
	private String maKH;

	@Column(name = "HO")
	private String ho;

	@Column(name = "TEN")
	private String ten;

	@Column(name = "GIOITINH")
	private String gioiTinh;

	@Column(name = "DIACHI")
	private String diaChi;

	@Column(name = "SDT")
	private String sdt;

	@Column(name = "MASOTHUE")
	private String maSoThue;

	@OneToOne
	@JoinColumn(name = "MATK")
	private TaiKhoanEntity maTK;

}
