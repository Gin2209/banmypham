package com.poly.asm_java6.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table (name = "order_detail")
public class order_detail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
     int quantity ;
     Double total;
     @ManyToOne
     @JoinColumn (name = "orderID")
     @JsonBackReference
     orders orders;
     @ManyToOne
     @JoinColumn (name = "productSizeID")
     ProductSize productSize;

}
