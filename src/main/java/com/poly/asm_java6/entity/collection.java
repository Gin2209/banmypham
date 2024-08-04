package com.poly.asm_java6.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table (name = "collection")
public class collection implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IDCollection;
    private String nameCollection;
    @OneToMany (mappedBy = "collection")
    @JsonManagedReference
    List<product> product;

    @Override
    public String toString() {
        return "Collection{" +
                "IDCollection=" + IDCollection +
                ", nameCollection='" + nameCollection + '\'' +
                // Chỉ hiển thị thông tin không tuần hoàn
                ", productCount=" + (product != null ? product.size() : 0) +
                '}';
    }
}
