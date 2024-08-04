package com.poly.asm_java6.dao;

import com.poly.asm_java6.entity.product_type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Product_typeDAO extends JpaRepository<product_type, String> {
}
