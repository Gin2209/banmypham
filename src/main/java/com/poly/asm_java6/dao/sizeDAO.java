package com.poly.asm_java6.dao;

import com.poly.asm_java6.entity.size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface sizeDAO extends JpaRepository<size, Integer> {
}