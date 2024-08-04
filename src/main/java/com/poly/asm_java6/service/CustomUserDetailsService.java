package com.poly.asm_java6.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.poly.asm_java6.dao.AccountDAO;
import com.poly.asm_java6.entity.account;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountDAO accountRepository;

    public CustomUserDetailsService(AccountDAO accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<account> user = accountRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        account acc = user.get();
        return new org.springframework.security.core.userdetails.User(acc.getUserID(),
                acc.getPassword(),
                new ArrayList<>());
    }
}
