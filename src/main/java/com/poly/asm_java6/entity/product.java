package com.poly.asm_java6.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID;
    private String name;
    private String image;
    private String description;

    @ManyToOne
    @JoinColumn(name = "IDCollection")
    @JsonBackReference
    private collection collection;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<images> imagesList;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<ProductSize> productSizeList;

    @Setter
    @Getter
    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<product_productType> productProductTypeList;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<product_skinConcern> productSkinConcerns;


    @Transient
    Double minPrice;

    @Transient
    Double maxPrice;

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", name='" + name + '\'' +
//                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
//                ", collection=" + collection +
//                ", imagesList=" + imagesList +
//                ", productSizeList=" + productSizeList +
//                ", productProductTypeList=" + productProductTypeList +
//                ", productSkinConcerns=" + productSkinConcerns +
                '}';
    }

    public List<product_productType> getProductProductTypeList() {
        return productProductTypeList;
    }

    public void setProductProductTypeList(List<product_productType> productProductTypeList) {
        this.productProductTypeList = productProductTypeList;
    }

}
