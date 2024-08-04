package com.poly.asm_java6.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table (name = "review")
public class review implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int reviewID ;
    int rating;
    String comment ;
    @Temporal(TemporalType.DATE)
    @Column(name = "reviewDate")
    Date reviewDate = new Date();
    @ManyToOne
    @JoinColumn (name = "orderID")
    @JsonBackReference
    orders orders;
}
