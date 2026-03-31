package com.example.demo.repo;

import com.example.demo.entity.Delivery;
import com.example.demo.entity.DeliveryStatus;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

     // ✅ total deliveries today
    @Query("SELECT COUNT(d) FROM Delivery d WHERE DATE(d.deliveryTime) = :date")
    long countByDeliveryDate(LocalDate date);

    // ✅ status-wise count
    @Query("SELECT COUNT(d) FROM Delivery d WHERE DATE(d.deliveryTime) = :date AND d.status = :status")
    long countByDeliveryDateAndStatus(LocalDate date, DeliveryStatus status);


    @Query("SELECT COUNT(d) FROM Delivery d WHERE DATE(d.deliveryTime) = CURRENT_DATE")
    long countToday();

    @Query("SELECT COUNT(d) FROM Delivery d WHERE d.status = :status AND DATE(d.deliveryTime) = CURRENT_DATE")
    long countByStatusToday(@Param("status") DeliveryStatus status);

    @Query("SELECT COUNT(d) FROM Delivery d WHERE DATE(d.deliveryTime) = CURRENT_DATE AND d.status = :status")
    long countByStatusToday2(@Param("status") DeliveryStatus status);
    // SELECT COUNT(*) FROM Delivery WHERE DATE(deliveryTime) = CURRENT_DATE AND status = :status
    
}