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
@Table(name = "order_customer")
public class OrderCustomerEntity {
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @Basic(optional = false)
    @Column(name = "order_date")
    private Long orderDate;
    @Basic(optional = false)
    @Column(name = "delivery_name")
    private String deliveryName;
    @Basic(optional = false)
    @Column(name = "delivery_adress")
    private String deliveryAdress;
    @Basic(optional = false)
    @Column(name = "delivery_phone")
    private String deliveryPhone;
    @Basic(optional = false)
    @Column(name = "delivery_email")
    private String deliveryEmail;
    @Basic(optional = false)
    @Column(name = "delivery_date")
    private Long deliveryDate;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "orderCustomerId")
    private List<CartDetailEntity> cartDetailList;
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UserEntity customerId;
    @JoinColumn(name = "approve_employee_id", referencedColumnName = "id")
    @ManyToOne
    private UserEntity approveEmployeeId;
    @JoinColumn(name = "delivery_employee_id", referencedColumnName = "id")
    @ManyToOne
    private UserEntity deliveryEmployeeId;
    @JoinColumn(name = "cancel_employee_id", referencedColumnName = "id")
    @ManyToOne
    private UserEntity cancelEmployeeId;
    @JoinColumn(name = "order_status_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private OrderStatusEntity orderStatusId;
}
