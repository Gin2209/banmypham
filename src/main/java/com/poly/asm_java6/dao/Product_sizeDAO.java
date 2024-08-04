package com.poly.asm_java6.dao;

import com.poly.asm_java6.entity.ProductSize;
import com.poly.asm_java6.entity.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Product_sizeDAO extends JpaRepository<ProductSize, Integer > {
    @Query("SELECT ps FROM ProductSize ps WHERE ps.product = :product ORDER BY ps.price ASC")
    List<ProductSize> findByProductOrderByPriceAsc(@Param("product") product product);


    List<ProductSize> findByProduct(product product);
    @Query("SELECT SUM(ps.quantity) FROM ProductSize ps")
    Long sumAllQuantities();
}