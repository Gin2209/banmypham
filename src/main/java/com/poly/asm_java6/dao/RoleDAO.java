package com.poly.asm_java6.dao;

import com.poly.asm_java6.entity.role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<role, Integer> {
    role findByRoleName(String roleName);
}
