package com.poly.asm_java6.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "authorities")
public class authorities implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String userID;
    private int roleId;
    @ManyToOne
    @JoinColumn(name = "roleId", insertable = false, updatable = false)
    role role;
    @ManyToOne
    @JoinColumn(name = "userID", insertable = false, updatable = false)
    @JsonBackReference
    account account;
}
