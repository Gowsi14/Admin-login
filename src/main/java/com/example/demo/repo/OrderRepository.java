package com.example.demo.repo;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    long countByOrderTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Order> findByOrderTimeBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT COALESCE(SUM(o.amount), 0) FROM Order o " +
           "WHERE o.orderTime >= :start AND o.orderTime < :end")
    Double sumAmountByOrderTimeBetween(@Param("start") LocalDateTime start,
                                       @Param("end") LocalDateTime end);
}