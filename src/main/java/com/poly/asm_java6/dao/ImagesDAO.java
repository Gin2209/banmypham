package com.poly.asm_java6.dao;
import com.poly.asm_java6.entity.images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesDAO extends JpaRepository<images , Integer> {
    List<images> findByProduct_ProductID(@Param("productID") Integer productID);
}
