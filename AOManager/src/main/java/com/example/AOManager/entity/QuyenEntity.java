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
@Table(name = "QUYEN")
public class QuyenEntity {
	
	@Id
    @Column(name = "MAQUYEN")
    private String maQuyen;
	
    @Column(name = "TENQUYEN")
    private String tenQuyen;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "maQuyen")
    private List<TaiKhoanEntity> taiKhoanList;
 
}
