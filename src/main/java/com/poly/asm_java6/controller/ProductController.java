package com.poly.asm_java6.controller;

import com.poly.asm_java6.dao.*;
import com.poly.asm_java6.entity.ProductSize;
import com.poly.asm_java6.entity.product;
import com.poly.asm_java6.entity.images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ProductController {

    @Autowired
    ProductDAO productDAO;

    @Autowired
    CollectionDAO collectionDAO;

    @Autowired
    Skin_concernDAO skinConcernDAO;

    @Autowired
    Product_typeDAO productTypeDAO;

    @Autowired
    Product_sizeDAO productSizeDAO;

    @Autowired
    sizeDAO sizeDAO;

    @Autowired
    ImagesDAO imagesDAO;

    @RequestMapping("/list")
    public String list(Model model,
                       @RequestParam("page") Optional<Integer> page,
                       @RequestParam("size") Optional<Integer> size,
                       @RequestParam("keywords") Optional<String> kw) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(8);        String keywords = kw.orElse("");

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<product> productPage = productDAO.findByNameContaining(keywords, pageable);

        productPage.forEach(product -> {
            double minPrice = product.getProductSizeList().stream()
                    .mapToDouble(ProductSize::getPrice)
                    .min().orElse(0);
            double maxPrice = product.getProductSizeList().stream()
                    .mapToDouble(ProductSize::getPrice)
                    .max().orElse(0);
            product.setMinPrice(minPrice);
            product.setMaxPrice(maxPrice);
        });


        // Add data for filters
        model.addAttribute("collections", collectionDAO.findAll());
        model.addAttribute("skinConcerns", skinConcernDAO.findAll());
        model.addAttribute("productTypes", productTypeDAO.findAll());

        model.addAttribute("productPage", productPage);
        model.addAttribute("keywords", keywords);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", currentPage);

        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "user/list";
    }

    @RequestMapping("/detail/{productId}")
    public String index(@PathVariable("productId") int productId, Model model) {
        Optional<product> productOpt = productDAO.findById(productId);
        if (!productOpt.isPresent()) {
            model.addAttribute("error", "Sản phẩm không tồn tại");
            return "error"; // Redirect to an error page
        }

        product sanpham = productOpt.get();

        // Get the product sizes sorted by price
        List<ProductSize> productSizes = productSizeDAO.findByProductOrderByPriceAsc(sanpham);

        // Get images associated with the product
        List<images> imagesList = imagesDAO.findByProduct_ProductID(productId);

        model.addAttribute("product", sanpham);
        model.addAttribute("productSize", productSizes);
        model.addAttribute("images", imagesList); // Ensure this is not null

        return "user/detail";
    }

}

