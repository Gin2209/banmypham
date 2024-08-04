package com.poly.asm_java6.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table (name = "transactions")
public class transactions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int id;
    Double totalmoney;
    @Temporal(TemporalType.DATE)
    @Column(name = "transactionDate")
    Date transactionDate = new Date();
    @ManyToOne
    @JoinColumn (name = "orderID")
    @JsonBackReference
    orders orders;
}
