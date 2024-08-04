package com.poly.asm_java6.service;

import com.poly.asm_java6.dao.AccountDAO;
import com.poly.asm_java6.dao.AuthoritiesDAO;
import com.poly.asm_java6.entity.account;
import com.poly.asm_java6.entity.authorities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private AccountDAO accountRepository;
    @Autowired
    private AuthoritiesDAO authoritiesRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");

        Optional<account> accountOpt = accountRepository.findByEmail(email);
        account account;
        if (accountOpt.isPresent()) {
            account = accountOpt.get();
        } else {
            String userID = "AC" + UUID.randomUUID().toString().substring(0, 8);
            account = new account();
            account.setUserID(userID);
            account.setEmail(email);
            account.setFirstName(oAuth2User.getAttribute("given_name"));
            account.setLastName(oAuth2User.getAttribute("family_name"));
            account.setAvatar(oAuth2User.getAttribute("picture"));
            accountRepository.save(account);
            authorities authority = new authorities();
            authority.setUserID(userID);
            authority.setRoleId(3);
            authoritiesRepository.save(authority);

        }

        return new CustomOAuth2User(oAuth2User);
    }
}
