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
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "birthday")
    private Long birthday;
    @Basic(optional = false)
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @Column(name = "status")
    private Boolean status;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "employeeId")
    private List<OrderSupplierEntity> orderSupplierList;
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RoleEntity roleId;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "employeeId")
    private List<PriceDetailEntity> priceDetailList;
    @OneToMany(mappedBy = "approveEmployeeId")
    private List<OrderCustomerEntity> orderCustomerList;
    @OneToMany(mappedBy = "deliveryEmployeeId")
    private List<OrderCustomerEntity> orderCustomerList1;
    @OneToMany(mappedBy = "cancelEmployeeId")
    private List<OrderCustomerEntity> orderCustomerList2;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "employeeId")
    private List<DeductionEntity> deductionList;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "employeeId")
    private List<ImportFormEntity> importFormList;
}
