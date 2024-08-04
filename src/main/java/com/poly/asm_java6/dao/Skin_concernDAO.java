package com.poly.asm_java6.dao;

import com.poly.asm_java6.entity.skin_concern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Skin_concernDAO extends JpaRepository<skin_concern, String> {
}
