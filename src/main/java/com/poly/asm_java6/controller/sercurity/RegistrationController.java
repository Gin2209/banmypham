package com.poly.asm_java6.controller.sercurity;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.asm_java6.dao.AccountDAO;
import com.poly.asm_java6.dao.AuthoritiesDAO;
import com.poly.asm_java6.dao.RoleDAO;
import com.poly.asm_java6.entity.account;
import com.poly.asm_java6.entity.authorities;
import com.poly.asm_java6.entity.role;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private AccountDAO accountRepository;
    @Autowired
    private AuthoritiesDAO authoritiesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String showRegistrationForm() {
        return "sercurity/registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") account user, Model model) {
        Optional<account> existingUser = accountRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            model.addAttribute("error", "Email: " + user.getEmail() + " đã tồn tại!");
            return "user/lognin_signup";
        }
        String userID = "AC" + UUID.randomUUID().toString().substring(0, 8);
        user.setUserID(userID);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        accountRepository.save(user);

        // Assign default role "User"
        authorities authority = new authorities();
        authority.setUserID(userID);
        authority.setRoleId(3);
        authoritiesRepository.save(authority);

        return "redirect:/login";
    }
}
