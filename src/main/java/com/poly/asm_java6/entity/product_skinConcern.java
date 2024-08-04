package com.poly.asm_java6.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table (name = "product_skin_concern")
public class product_skinConcern implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int  IDSkin;
    @ManyToOne
    @JoinColumn (name = "skinConcernID")
    skin_concern skinConcern;
    @ManyToOne
    @JoinColumn (name = "productID")
    @JsonBackReference
    product product;
}
