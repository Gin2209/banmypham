package com.poly.asm_java6.dao;


import com.poly.asm_java6.entity.orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDAO extends JpaRepository<orders, String> {
    @Override
    long count();

    // Đếm số lượng đơn hàng hoàn thành
    @Query("SELECT COUNT(o) FROM orders o WHERE LOWER(LTRIM(RTRIM(o.status))) = LOWER(LTRIM(RTRIM(:status)))")
    long countOrdersByStatus(@Param("status") String status);



}

