package com.example.AOManager.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PHIEUNHAP")
public class PhieuNhapEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAPN")
    private Integer maPN;
	
    @Column(name = "NGAYTAO")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayTao;
    
    @ManyToOne   
    @JoinColumn(name = "MANV")
    private NhanVienEntity maNV;
    
    @OneToOne
    @JoinColumn(name = "MAPD")
    private PhieuDatEntity maPD;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maPN")
    private List<CT_PNEntity> cT_PNList;

}
