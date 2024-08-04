package com.poly.asm_java6.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table (name = "orders")
public class orders implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    String orderID ;
    @Temporal(TemporalType.DATE)
    @Column(name = "order_date")
    Date order_date = new Date();
    @Temporal(TemporalType.DATE)
    @Column(name = "expectedDeliveryDate")
    Date expectedDeliveryDate = new Date();
    String status;
    @ManyToOne
    @JoinColumn(name = "userID")
    @JsonBackReference
    account account;
    @OneToMany (mappedBy = "orders")
    @JsonManagedReference
    List<order_detail> orderdetails;
    @OneToMany (mappedBy = "orders")
    @JsonManagedReference
    List<review> reviews;
    @OneToMany (mappedBy = "orders")
    @JsonManagedReference
    List<transactions> transactions;

}
