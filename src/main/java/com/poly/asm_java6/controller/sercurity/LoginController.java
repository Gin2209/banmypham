package com.poly.asm_java6.controller.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.asm_java6.dao.AccountDAO;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    AccountDAO accountDAO;

    @GetMapping
    public String showLoginForm() {

        return "user/lognin_signup";
    }

}
