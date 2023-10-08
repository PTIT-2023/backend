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
@Table(name = "import_form")
public class ImportFormEntity {
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @Basic(optional = false)
    @Column(name = "create_date")
    private Long createDate;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "importId")
    private List<ImportDetailEntity> importDetailList;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UsersEntity employeeId;
    @JoinColumn(name = "order_supplier_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private OrderSupplierEntity orderSupplierId;
}
