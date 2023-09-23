package com.example.AOManager.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GIOHANG")
public class GioHangEntity {
	
	@Id
    @Column(name = "MAGH")
    private String maGH;
	
    @Column(name = "NGAYDAT")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayDat;
    
    @Column(name = "TEN")
    private String ten;
    
    @Column(name = "DIACHINHAN")
    private String diaChiNhan;
    
    @Column(name = "SDTNHAN")
    private String sDTNhan;
    
    @Column(name = "EMAILNHAN")
    private String emailNhan;
    
    @Column(name = "NGAYGIAO")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayGiao;
    
    @ManyToOne
    @JoinColumn(name = "MAHD")
    private HoaDonEntity maHD;
    
    @ManyToOne
	@JoinColumn(name = "MATT")
	private TrangThaiGHEntity maTT;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maGH")
    private List<CT_GHEntity> cT_GHList;
    
    @ManyToOne
    @JoinColumn(name = "MAKH")
    @NotNull
    private KhachHangEntity maKH;

}
