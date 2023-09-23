package com.example.AOManager.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
@Table(name = "NHANVIEN")
public class NhanVienEntity {

	@Id
    @Column(name = "maNV")
    private String maNV;
	
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
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maNV")
    private List<PhieuNhapEntity> phieuNhapList;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maNV")
    private List<KhuyenMaiEntity> khuyenMaiList;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maNV")
    private List<PhieuDatEntity> phieuDatList;
  
    @OneToOne
    @JoinColumn(name = "MATK")
    private TaiKhoanEntity maTK;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maNV")
    private List<ThayDoiGiaEntity> thayDoiGiaList;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maNV")
    private List<HoaDonEntity> hoaDonList;

}
