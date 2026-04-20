package com.example.demo.repo;

import com.example.demo.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    long countByStatus(String status);

    @Query("SELECT s FROM Subscription s WHERE s.status = :status")
        List<Subscription> findByStatus(@Param("status") String status);

}