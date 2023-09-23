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
@Table(name = "PHIEUDAT")
public class PhieuDatEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAPD")
    private Integer maPD;
	
    @Column(name = "NGAYDAT")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayDat;
    
    @Column(name = "MAPN")
    private String maPN;
    
    @OneToOne(cascade = CascadeType.REFRESH, mappedBy = "maPD")
    private PhieuNhapEntity phieuNhap;
    
    @ManyToOne
    @JoinColumn(name = "MANCC")
    private NhaCungCapEntity maNCC;
    
    @ManyToOne
    @JoinColumn(name = "MANV")
    private NhanVienEntity maNV;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maPD")
    private List<CT_PDEntity> cT_PDList;
    
}
