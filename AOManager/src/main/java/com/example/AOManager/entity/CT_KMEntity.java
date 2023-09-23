package com.example.AOManager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CTKM")
public class CT_KMEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
	
    @Column(name = "PHANTRAMGIAM")
    private double phanTramGiam;
    
    @ManyToOne
    @JoinColumn(name = "MAKM")
    private KhuyenMaiEntity maKM;
    
    @ManyToOne
    @JoinColumn(name = "MASVC")
    private SinhVatCanhEntity maSVC;
	
}
