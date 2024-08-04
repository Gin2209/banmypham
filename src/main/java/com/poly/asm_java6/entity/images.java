package com.poly.asm_java6.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;

@Data
@Entity
@Table (name = "images")
public class images implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int imgID ;
    String imagePath;
    @ManyToOne
    @JoinColumn (name = "productID")
    @JsonBackReference
    product product;
//    @Override
//    public String toString() {
//        return "Images{" +
//                "imgID=" + imgID +
////                ", imagePath='" + imagePath + '\'' +
//                // Tránh hiển thị thuộc tính product để tránh vòng lặp
//                '}';
//    }

}
