// import jakarta.persistence.*;
// import lombok.*;

// import java.time.LocalDateTime;

// @Entity
// @Table(name = "orders")
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class Order {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(name = "customer_name")
//     private String customerName;

//     @Column(name = "phone")
//     private String phone;

//     @Column(name = "pincode")
//     private String pincode;

//     @Column(name = "plan")
//     private String plan;

//     @Column(name = "status")
//     private String status;

//     @Column(name = "amount")
//     private Double amount;

//     @Column(name = "order_time")
//     private LocalDateTime orderTime;

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
//     public String getCustomerName() { return customerName; }
//     public void setCustomerName(String customerName) { this.customerName = customerName; }
//     public String getPhone() { return phone; }
//     public void setPhone(String phone) { this.phone = phone; }
//     public String getPincode() { return pincode; }
//     public void setPincode(String pincode) { this.pincode = pincode; }
//     public String getPlan() { return plan; }
//     public void setPlan(String plan) { this.plan = plan; }
//     public String getStatus() { return status; }
//     public void setStatus(String status) { this.status = status; }
//     public Double getAmount() { return amount; }
//     public void setAmount(Double amount) { this.amount = amount; }
//     public LocalDateTime getOrderTime() { return orderTime; }
//     public void setOrderTime(LocalDateTime orderTime) { this.orderTime = orderTime; }
// }

package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    private String phone;
    private String plan;
    private String pincode;
    private double amount;
    private String status;

    @Column(name = "order_time")
    private LocalDateTime orderTime;
}