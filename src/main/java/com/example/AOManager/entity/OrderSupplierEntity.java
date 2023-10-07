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
@Table(name = "order_supplier")
public class OrderSupplierEntity {
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @Basic(optional = false)
    @Column(name = "supplier_name")
    private String supplierName;
    @Basic(optional = false)
    @Column(name = "order_date")
    private Long orderDate;
    @Basic(optional = false)
    @Column(name = "delivery_date")
    private Long deliveryDate;
    @Column(name = "import_id")
    private String importId;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "orderSupplierId")
    private List<OrderSupplierDetailEntity> orderSupplierDetailList;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UserEntity employeeId;
    @OneToOne(cascade = CascadeType.REFRESH, mappedBy = "orderSupplierId")
    private ImportFormEntity importForm;
}
