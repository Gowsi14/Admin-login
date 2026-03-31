package com.example.demo.repo;

import com.example.demo.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    long countByStatus(String status);

    List<Subscription> findByStatus(String status);
}