package com.example.demo.repo;

import com.example.demo.entity.Delivery;
import com.example.demo.entity.DeliveryStatus;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    @Query("SELECT COUNT(d) FROM Delivery d WHERE d.deliveryTime >= :start AND d.deliveryTime < :end")
    long countByDeliveryDate(@Param("start") LocalDateTime start,
                             @Param("end") LocalDateTime end);

    @Query("SELECT COUNT(d) FROM Delivery d WHERE d.deliveryTime >= :start AND d.deliveryTime < :end AND d.status = :status")
    long countByDeliveryDateAndStatus(@Param("start") LocalDateTime start,
                                      @Param("end") LocalDateTime end,
                                      @Param("status") DeliveryStatus status);
}