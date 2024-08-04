package com.poly.asm_java6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.asm_java6.dao.AccountDAO;
import com.poly.asm_java6.dao.AddressDAO;
import com.poly.asm_java6.entity.account;
import com.poly.asm_java6.entity.address;

@Service
public class AccountService {

    @Autowired
    private AccountDAO accountDAO;

    public account findByUserId(String userId) {
        return accountDAO.findById(userId).orElse(null);
    }

    public account updateAccount(account account) {
        return accountDAO.save(account);
    }
}
