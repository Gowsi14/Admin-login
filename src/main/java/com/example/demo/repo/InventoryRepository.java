package com.example.demo.repo;

import com.example.demo.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    long countByStatus(String status);
    List<Inventory> findByStatus(String status);
}