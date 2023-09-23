package com.example.AOManager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	
}
