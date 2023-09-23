package com.example.AOManager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
