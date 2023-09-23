package com.example.AOManager.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "KHUYENMAI")
public class KhuyenMaiEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAKM")
    private Integer maKM;
	
    @Column(name = "MOTA")
    private String moTa;
    
    @Column(name = "NGAYBD")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayBD;
    
    @Column(name = "NGAYKT")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayKT;
    
    @ManyToOne
    @JoinColumn(name = "MANV")
    private NhanVienEntity maNV;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maKM")
    private List<CT_KMEntity> cT_KMList;
    
}
