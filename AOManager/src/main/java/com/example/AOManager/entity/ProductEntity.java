package com.example.AOManager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @Basic(optional = false)
    @Column(name = "price")
    private Long price;
    @Column(name = "description")
    private String description;
    @Column(name = "habitat")
    private String habitat;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
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
}
