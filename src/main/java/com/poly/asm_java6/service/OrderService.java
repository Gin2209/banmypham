package com.poly.asm_java6.service;

import com.poly.asm_java6.DTO.MonthlyRevenueDTO;
import com.poly.asm_java6.dao.OrderDetailDAO;
import com.poly.asm_java6.dao.OrdersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrdersDAO ordersDAO;

    public long getNumberOrderID() {
        return ordersDAO.count();
    }
    @Autowired
    private OrderDetailDAO orderDetailDAO;

    public List<MonthlyRevenueDTO> getMonthlyRevenue() {
        return orderDetailDAO.getMonthlyRevenue();
    }
    //best sales
    @Autowired
    private OrderDetailDAO OrderDetailDAO;

    public List<Object[]> getTopSellingProducts() {
        Pageable pageable = PageRequest.of(0, 5); // Lấy 5 sản phẩm bán chạy nhất
        return orderDetailDAO.findTopSellingProducts(pageable);
    }
}
