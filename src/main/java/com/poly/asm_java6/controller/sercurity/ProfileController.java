package com.poly.asm_java6.controller.sercurity;

import com.poly.asm_java6.dao.AccountDAO;
import com.poly.asm_java6.dao.AddressDAO; // Thêm import cho AddressDAO
import com.poly.asm_java6.entity.account;
import com.poly.asm_java6.entity.address;
import com.poly.asm_java6.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List; // Thêm import cho List
import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AddressDAO addressDAO; // Thêm AddressDAO vào đây

    @GetMapping("/profile/{userId}")
    public String getProfile(@PathVariable String userId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<account> user = accountDAO.findByUserID(authentication.getName());
        if (user.isPresent()) {
            String fullName = user.get().getFirstName() + " " + user.get().getLastName();
            model.addAttribute("userName", fullName);
        }

        account account = accountService.findByUserId(userId);
        if (account.getAddress() == null || account.getAddress().isEmpty()) {
            account.setAddress(new ArrayList<>());
        }
        model.addAttribute("account", account);
        return "user/lognin_up";
    }

    @PostMapping("/profile/{userId}")
    public String updateProfile(@PathVariable String userId, @ModelAttribute("account") account account, Model model) {
        account existingAccount = accountService.findByUserId(userId);
        if (existingAccount != null) {
            existingAccount.setFirstName(account.getFirstName());
            existingAccount.setLastName(account.getLastName());
            existingAccount.setEmail(account.getEmail());
            existingAccount.setPhoneNumber(account.getPhoneNumber());

            if (account.getAddress() != null && !account.getAddress().isEmpty()) {
                List<address> updatedAddresses = new ArrayList<>();
                for (address addr : account.getAddress()) {
                    address existingAddress = addressDAO.findByAddress(addr.getAddress());
                    if (existingAddress == null) {
                        addr.setAccount(existingAccount);
                        addressDAO.save(addr);
                        updatedAddresses.add(addr);
                    } else {
                        existingAddress.setWard(addr.getWard());
                        existingAddress.setDistrict(addr.getDistrict());
                        existingAddress.setCity(addr.getCity());
                        existingAddress.setAccount(existingAccount);
                        addressDAO.save(existingAddress);
                        updatedAddresses.add(existingAddress);
                    }
                }
                existingAccount.setAddress(updatedAddresses);
            } else {
                existingAccount.setAddress(new ArrayList<>());
            }

            accountService.updateAccount(existingAccount);
        }

        return "redirect:/profile/" + userId;
    }
}
