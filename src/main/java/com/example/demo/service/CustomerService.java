package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.Customer;
import com.example.demo.repo.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private CustomerResponse toResponse(Customer c) {
        return CustomerResponse.builder()
                .customerId(c.getId())
                .name(c.getName())
                .phone(c.getPhone())
                .area(c.getArea())
                .pincode(c.getPincode())
                .district(c.getDistrict())
                .zone(c.getZone())
                .plan(c.getPlan())
                .lastOrder(c.getLastOrder())
                .status(c.getStatus())
                .build();
    }

    public CustomerSummaryResponse getSummary() {
        long total = customerRepository.count();
        long active = customerRepository.countByStatus("ACTIVE");
        long today = customerRepository.countByLastOrder(LocalDate.now());
        long inactive = customerRepository.countByStatus("INACTIVE");
        return new CustomerSummaryResponse(total, active, today, inactive);
    }

    public List<CustomerAreaResponse> getByArea() {
        return customerRepository.countByArea().stream()
                .map(row -> new CustomerAreaResponse(
                        (String) row[0],
                        ((Number) row[1]).longValue(),
                        ((Number) row[2]).longValue()))
                .collect(Collectors.toList());
    }

    public List<CustomerAreaResponse> getByZone() {
        return customerRepository.countByZone().stream()
                .map(row -> new CustomerAreaResponse(
                        (String) row[0],
                        ((Number) row[1]).longValue(),
                        ((Number) row[2]).longValue()))
                .collect(Collectors.toList());
    }

    public List<CustomerAreaResponse> getByDistrict() {
        return customerRepository.countByDistrict().stream()
                .map(row -> new CustomerAreaResponse(
                        (String) row[0],
                        ((Number) row[1]).longValue(),
                        ((Number) row[2]).longValue()))
                .collect(Collectors.toList());
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream().map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CustomerResponse create(Customer customer) {
        return toResponse(customerRepository.save(customer));
    }

    public CustomerResponse update(Long id, Customer updated) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        c.setName(updated.getName());
        c.setPhone(updated.getPhone());
        c.setArea(updated.getArea());
        c.setPincode(updated.getPincode());
        c.setDistrict(updated.getDistrict());
        c.setZone(updated.getZone());
        c.setPlan(updated.getPlan());
        c.setStatus(updated.getStatus());
        c.setLastOrder(updated.getLastOrder());
        return toResponse(customerRepository.save(c));
    }

    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}