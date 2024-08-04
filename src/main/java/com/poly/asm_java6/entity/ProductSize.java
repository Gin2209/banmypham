package com.poly.asm_java6.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table (name = "product_size")

public class ProductSize implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int productSizeID;
    Double price ;
    int quantity;
    @ManyToOne
    @JoinColumn (name = "productID")
    @JsonBackReference
    product product;
    @ManyToOne
    @JoinColumn (name = "sizeID")
    @JsonManagedReference
    size size;
    @Transient
    private String unit;
    @Override
    public String toString() {
        return "ProductSize{" +
                "productSizeID=" + productSizeID +
//                ", price=" + price +
                ", quantity=" + quantity +
                // Tránh hiển thị thuộc tính product và size để tránh vòng lặp
                '}';
    }
}
