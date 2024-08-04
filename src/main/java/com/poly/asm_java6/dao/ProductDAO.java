package com.poly.asm_java6.dao;

import com.poly.asm_java6.entity.product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<product, Integer> {

    @Query("SELECT p FROM product p JOIN p.productProductTypeList ppt JOIN ppt.product_type pt WHERE pt.name IN :names")
    List<product> findByProductTypes_NameIn(@Param("names") List<String> names);

    Page<product> findByNameContaining(String name, Pageable pageable);

}
