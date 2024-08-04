package com.poly.asm_java6.controller;

import com.poly.asm_java6.DTO.MonthlyRevenueDTO;
import com.poly.asm_java6.dao.*;
import com.poly.asm_java6.entity.*;
import com.poly.asm_java6.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    ProductDAO productDAO;

    @Autowired
    CollectionDAO collectionDAO;

    @Autowired
    Skin_concernDAO skinConcernDAO;

    @Autowired
    Product_typeDAO productTypeDAO;

    @Autowired
    ImagesDAO imagesDAO;

    @Autowired
    sizeDAO sizeDAO;
    @Autowired
    OrderDetailDAO orderDetailDAO;
    @Autowired
    Product_sizeDAO productSizeDAO;
    @Autowired
    OrdersDAO ordersDAO;

    @Autowired
    OrderService orderService;

    @RequestMapping("/admin/qlsanpham")
    public String index(Model model) {
        List<product> products = productDAO.findAll();
        List<collection> collections = collectionDAO.findAll();
        List<skin_concern> skin_concerns = skinConcernDAO.findAll();
        List<product_type> product_types = productTypeDAO.findAll();
        List<images> images = imagesDAO.findAll();

        model.addAttribute("collections", collections);
        model.addAttribute("skinConcerns", skin_concerns);
        model.addAttribute("productTypes", product_types);
        model.addAttribute("products", products);
        model.addAttribute("images", images);
        return "admin/qlsanpham";
    }

    @RequestMapping("/new")
    public String showNewProductPage(Model model) {
        product product = new product();
        model.addAttribute("product", product);
        return "admin/chinhsuasp";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") product product) {
        productDAO.save(product);
        return "redirect:/admin/qlsanpham";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("admin/chinhsuasp");
        product product = productDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        mav.addObject("product", product);

        List<collection> collections = collectionDAO.findAll();
        mav.addObject("collections", collections);
        List<size> size = sizeDAO.findAll();
        mav.addObject("sizes", size);
        List<skin_concern> skin_concerns = skinConcernDAO.findAll();
        List<product_type> product_types = productTypeDAO.findAll();
        mav.addObject("skinConcerns", skin_concerns);
        mav.addObject("productTypes", product_types);
        return mav;
    }

    // @PostMapping("/update/{id}")
    // public String updateProduct(@PathVariable("id") Integer id, @Validated
    // @ModelAttribute("product") product product,
    // BindingResult result, Model model) {
    // if (result.hasErrors()) {
    // product.setProductID(id);
    // return "admin/chinhsuasp";
    // }
    //
    // productDAO.save(product);
    // return "redirect:/admin/qlsanpham";
    // }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, Model model) {
        product product = productDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        productDAO.delete(product);
        return "redirect:/admin/qlsanpham";
    }

    @GetMapping({ "/admin/thongke", "/admin" })
    @PreAuthorize("hasRole('ROLE_Admin')")
    public String viewStatisticsPage(Model model) {
        // Tính tổng doanh thu từ order_detail
        Double totalRevenue = orderDetailDAO.calculateTotalRevenue();
        model.addAttribute("totalRevenue", totalRevenue);
        Long totalQuantity = productSizeDAO.sumAllQuantities();
        model.addAttribute("totalQuantity", totalQuantity);

        long completedOrders = ordersDAO.countOrdersByStatus("Đã hoàn thành");
        long pendingOrders = ordersDAO.countOrdersByStatus("Chưa hoàn thành");

        model.addAttribute("completedOrders", completedOrders);
        model.addAttribute("pendingOrders", pendingOrders);
        // chart
        List<MonthlyRevenueDTO> monthlyRevenues = orderService.getMonthlyRevenue();

        // In ra kết quả để kiểm tra
        for (MonthlyRevenueDTO revenue : monthlyRevenues) {
            System.out.println("Year: " + revenue.getYear() + ", Month: " + revenue.getMonth() + ", Total Revenue: "
                    + revenue.getTotalRevenue());
        }
        model.addAttribute("monthlyRevenues", monthlyRevenues);
        // best sales
        // Lấy danh sách các sản phẩm bán chạy nhất từ dịch vụ
        List<Object[]> topSellingProducts = orderService.getTopSellingProducts();

        // Thêm danh sách này vào model để gửi tới view
        model.addAttribute("topSellingProducts", topSellingProducts);
        return "admin/thongke";
    }

}
