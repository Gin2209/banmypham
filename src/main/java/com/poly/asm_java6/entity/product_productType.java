package com.poly.asm_java6.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;

@Data
@Entity
@Table (name = "product_product_type")
public class product_productType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn (name ="productTypeID")
    product_type product_type;
    @ManyToOne
    @JoinColumn (name ="productID")
    @JsonBackReference
    product product;

    // Getters and setters
    public product_type getProductType() {
        return product_type;
    }

    public void setProductType(product_type productType) {
        this.product_type = productType;
    }
}
