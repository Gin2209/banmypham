package com.poly.asm_java6.controller;

import com.poly.asm_java6.dao.AccountDAO;
import com.poly.asm_java6.dao.CollectionDAO;
import com.poly.asm_java6.dao.ProductDAO;
import com.poly.asm_java6.dao.Product_typeDAO;
import com.poly.asm_java6.dao.Skin_concernDAO;
import com.poly.asm_java6.entity.account;
import com.poly.asm_java6.entity.collection;
import com.poly.asm_java6.entity.product;
import com.poly.asm_java6.entity.product_skinConcern;
import com.poly.asm_java6.entity.skin_concern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class HomeController {

        @Autowired
        ProductDAO productDAO;

        @Autowired
        CollectionDAO collectionDAO;

        @Autowired
        Skin_concernDAO skinConcernDAO;

        @Autowired
        Product_typeDAO productTypeDAO;

        @Autowired
        AccountDAO accountDAO;

        @RequestMapping({ "/product/index", "/" })
        public String index(Model model) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null && authentication.isAuthenticated()) {
                        model.addAttribute("username", authentication.getName());

                        if (authentication.getPrincipal() instanceof OAuth2User) {
                                OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
                                String firstName = oAuth2User.getAttribute("given_name");
                                String lastName = oAuth2User.getAttribute("family_name");
                                model.addAttribute("userName", firstName + " " + lastName);
                        } else if (authentication.getPrincipal() instanceof User) {
                                Optional<account> user = accountDAO.findByUserID(authentication.getName());
                                String fullName = user.get().getFirstName() + " " + user.get().getLastName();
                                if (user.isPresent()) {
                                        model.addAttribute("userName", fullName);
                                }
                        }
                }

                List<skin_concern> skins = skinConcernDAO.findAll();
                List<product> products = productDAO.findAll();
                List<collection> collections = collectionDAO.findAll();
                model.addAttribute("collections", collections);

                // Nhóm các sản phẩm theo skin concern
                Map<String, List<product>> productsBySkinConcern = products.stream()
                                .filter(product -> product.getProductSkinConcerns() != null
                                                && !product.getProductSkinConcerns().isEmpty())
                                .filter(product -> product.getProductSkinConcerns().get(0).getSkinConcern() != null)
                                .collect(Collectors.groupingBy(
                                                product -> product.getProductSkinConcerns().get(0).getSkinConcern()
                                                                .getSkinConcernID()));

                // Gán danh sách sản phẩm cho từng skin concern
                for (skin_concern skin : skins) {
                        List<product> filteredProducts = productsBySkinConcern.getOrDefault(skin.getSkinConcernID(),
                                        List.of());
                        skin.setProuctSkinConcernList(
                                        filteredProducts.stream()
                                                        .map(product -> {
                                                                product_skinConcern psc = new product_skinConcern();
                                                                psc.setSkinConcern(skin);
                                                                psc.setProduct(product);
                                                                return psc;
                                                        })
                                                        .collect(Collectors.toList()));
                }

                // Nhóm các sản phẩm theo collection
                Map<Integer, List<product>> productsByCollection = products.stream()
                                .collect(Collectors.groupingBy(product -> product.getCollection().getIDCollection()));

                // Gán danh sách sản phẩm cho từng collection
                for (collection col : collections) {
                        col.setProduct(
                                        productsByCollection.getOrDefault(col.getIDCollection(), List.of()));
                }

                model.addAttribute("skins", skins);
                return "index";
        }
}
