package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String phone;
    private String pincode;
    private String address;

    private LocalDateTime deliveryTime;

    // ✅ ONLY ONE status field
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    // ✅ Default constructor
    public Delivery() {}

    // ✅ Constructor
    public Delivery(Long id, String customerName, String phone,
                    String pincode, String address,
                    LocalDateTime deliveryTime,
                    DeliveryStatus status) {
        this.id = id;
        this.customerName = customerName;
        this.phone = phone;
        this.pincode = pincode;
        this.address = address;
        this.deliveryTime = deliveryTime;
        this.status = status;
    }

    // ✅ Getters & Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public LocalDateTime getDeliveryTime() { return deliveryTime; }
    public void setDeliveryTime(LocalDateTime deliveryTime) { this.deliveryTime = deliveryTime; }

    public DeliveryStatus getStatus() { return status; }
    public void setStatus(DeliveryStatus status) { this.status = status; }
}