package com.poly.asm_java6.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.asm_java6.entity.address;

public interface AddressDAO extends JpaRepository<address, String> {
    boolean existsByAddress(String address);

    address findByAddress(String address);
}