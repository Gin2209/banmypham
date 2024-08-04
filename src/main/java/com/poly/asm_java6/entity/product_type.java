package com.poly.asm_java6.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table (name = "product_type")
public class product_type implements Serializable {
    @Id
    String productTypeID ;
    String name;
    @OneToMany (mappedBy = "product_type")
    @JsonBackReference
    List<product_productType> productProductTypeList;
    @Override
    public String toString() {
        return "product_type{" +
                "productTypeID='" + productTypeID + '\'' +
                ", name='" + name + '\'' +
                // Tránh hiển thị danh sách productProductTypeList để tránh vòng lặp
                '}';
    }
}
