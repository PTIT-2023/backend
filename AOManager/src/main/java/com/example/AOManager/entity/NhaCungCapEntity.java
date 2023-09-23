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
@Table(name = "NHACUNGCAP")
public class NhaCungCapEntity {
	
    @Id
    @Column(name = "MANCC")
    private String maNCC;
    
    @Column(name = "TENNCC") 
    private String tenNCC;
    
    @Column(name = "DIACHI")
    private String diaChi;
    
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "SDT")
    private String sDT;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maNCC")
    private List<CT_CCEntity> cT_CCList;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maNCC")
    private List<PhieuDatEntity> phieuDatList;
    
}
