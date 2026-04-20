package com.example.demo.rest;

import com.example.demo.dto.*;
import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/summary")
    public ResponseEntity<CustomerSummaryResponse> getSummary() {
        return ResponseEntity.ok(customerService.getSummary());
    }

    @GetMapping("/by-area")
    public ResponseEntity<List<CustomerAreaResponse>> getByArea() {
        return ResponseEntity.ok(customerService.getByArea());
    }

    @GetMapping("/by-zone")
    public ResponseEntity<List<CustomerAreaResponse>> getByZone() {
        return ResponseEntity.ok(customerService.getByZone());
    }

    @GetMapping("/by-district")
    public ResponseEntity<List<CustomerAreaResponse>> getByDistrict() {
        return ResponseEntity.ok(customerService.getByDistrict());
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAll() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.create(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable Long id,
                                                    @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.update(id, customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}