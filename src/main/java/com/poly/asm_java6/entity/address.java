package com.poly.asm_java6.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "address")
public class address implements Serializable {
    @Id
    String address;
    @Column(nullable = true)
    private String ward;

    @Column(nullable = true)
    private String district;

    @Column(nullable = true)
    private String city;

    @ManyToOne
    @JoinColumn(name = "userID")
    @JsonBackReference
    account account;
}
