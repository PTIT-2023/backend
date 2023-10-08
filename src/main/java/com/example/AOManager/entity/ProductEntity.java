package com.example.AOManager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "habitat")
    private String habitat;
    @Column(name = "temperature")
    private Double temperature;
    @Column(name = "ph")
    private Double ph;
    @Column(name = "position")
    private String position;
    @Column(name = "reproduction_method")
    private String reproductionMethod;
    @Column(name = "food_type")
    private String foodType;
    @Column(name = "max_size")
    private Double maxSize;
    @Basic(optional = false)
    @Column(name = "inventory_quantity")
    private Integer inventoryQuantity;
    @Basic(optional = false)
    @Column(name = "sold_quantity")
    private Integer soldQuantity;
    @Basic(optional = false)
    @Column(name = "status")
    private Boolean status;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CategoryEntity categoryId;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "productId")
    private List<ProductImageEntity> productImageList;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "productId")
    private List<ImportDetailEntity> importDetailList;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "productId")
    private List<OrderSupplierDetailEntity> orderSupplierDetailList;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "productId")
    private List<CartDetailEntity> cartDetailList;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "productId")
    private List<PriceDetailEntity> priceDetailList;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "productId")
    private List<DeductionEntity> deductionList;

    public List<String> getImageListString() {
        List<String> imageListString = new ArrayList<>();
        for(ProductImageEntity productImageEntity : productImageList) {
            imageListString.add(productImageEntity.getUrl());
        }
        return imageListString;
    }

    public long getCurrentPrice() {
        List<PriceDetailEntity> priceDetailList = this.priceDetailList;
        long currentDate = new Date().getTime();
        long min = 100000000;
        int vt = -1;
        for(int i = 0; i < priceDetailList.size(); i++){
            long getDiff = currentDate - priceDetailList.get(i).getApplyDate();
            long getDaysDiff = getDiff / (24 * 60 * 60 * 1000);
            if(getDaysDiff >= 0 && getDaysDiff < min){
                min = getDaysDiff;
                vt = i;
            }
        }
        if((priceDetailList.size() > 0) && (vt >= 0)) return priceDetailList.get(vt).getPrice();
        return 0;
    }
}
