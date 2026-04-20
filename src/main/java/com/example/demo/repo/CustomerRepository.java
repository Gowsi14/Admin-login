package com.example.demo.repo;

import com.example.demo.entity.Customer;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    long countByStatus(String status);

    long countByLastOrder(LocalDate date);

    List<Customer> findByStatus(String status);

    @Query("SELECT c.area, COUNT(c), SUM(CASE WHEN c.status = 'ACTIVE' THEN 1 ELSE 0 END) " +
           "FROM Customer c GROUP BY c.area")
    List<Object[]> countByArea();

    @Query("SELECT c.zone, COUNT(c), SUM(CASE WHEN c.status = 'ACTIVE' THEN 1 ELSE 0 END) " +
           "FROM Customer c GROUP BY c.zone")
    List<Object[]> countByZone();

    @Query("SELECT c.district, COUNT(c), SUM(CASE WHEN c.status = 'ACTIVE' THEN 1 ELSE 0 END) " +
           "FROM Customer c GROUP BY c.district")
    List<Object[]> countByDistrict();
}