package com.poly.asm_java6.dao;

import com.poly.asm_java6.entity.collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface CollectionDAO extends JpaRepository<collection, Integer> {
}
