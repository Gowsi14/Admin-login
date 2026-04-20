package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String area;
    private String pincode;
    private String district;
    private String zone;
    private String plan;
    private String status;
    private LocalDate lastOrder;

    public Customer() {}

    public Customer(Long id, String name, String phone, String area, String pincode,
                    String district, String zone, String plan, String status, LocalDate lastOrder) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.area = area;
        this.pincode = pincode;
        this.district = district;
        this.zone = zone;
        this.plan = plan;
        this.status = status;
        this.lastOrder = lastOrder;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getZone() { return zone; }
    public void setZone(String zone) { this.zone = zone; }

    public String getPlan() { return plan; }
    public void setPlan(String plan) { this.plan = plan; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getLastOrder() { return lastOrder; }
    public void setLastOrder(LocalDate lastOrder) { this.lastOrder = lastOrder; }
}