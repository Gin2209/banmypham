package com.poly.asm_java6.dao;


import com.poly.asm_java6.DTO.MonthlyRevenueDTO;
import com.poly.asm_java6.entity.order_detail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface OrderDetailDAO extends JpaRepository<order_detail, Integer> {

    @Query("SELECT SUM(od.total) FROM order_detail od")
    Double calculateTotalRevenue();
    //chart
    @Query("SELECT new com.poly.asm_java6.DTO.MonthlyRevenueDTO(YEAR(o.order_date), MONTH(o.order_date), SUM(od.total)) " +
            "FROM order_detail od JOIN od.orders o " +
            "GROUP BY YEAR(o.order_date), MONTH(o.order_date) " +
            "ORDER BY YEAR(o.order_date), MONTH(o.order_date)")
    List<MonthlyRevenueDTO> getMonthlyRevenue();
    //best sales
    @Query("SELECT p.name, SUM(od.quantity) AS TotalQuantity " +
            "FROM order_detail od " +
            "JOIN od.productSize ps " +
            "JOIN ps.product p " +
            "GROUP BY p.name " +
            "ORDER BY TotalQuantity DESC")
    List<Object[]> findTopSellingProducts(Pageable pageable);
}