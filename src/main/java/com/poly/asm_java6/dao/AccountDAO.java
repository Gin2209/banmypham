package com.poly.asm_java6.dao;

import com.poly.asm_java6.entity.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO extends JpaRepository<account, String> {
    Optional<account> findByEmail(String email);

    Optional<account> findByUserID(String userID);
}
