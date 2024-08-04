package com.poly.asm_java6.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table (name = "size")
public class size implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int sizeiD ;
    int size;
    String unit;
    @OneToMany (mappedBy = "size")
    @JsonBackReference
    List<ProductSize> productSizes;
}
