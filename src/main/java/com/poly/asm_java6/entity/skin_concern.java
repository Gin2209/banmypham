package com.poly.asm_java6.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table (name = "skin_concern")
public class skin_concern {
    @Id
    String skinConcernID;
    String name;
    @OneToMany (mappedBy = "skinConcern")
    @JsonBackReference
    List<product_skinConcern> prouctSkinConcernList;



}
