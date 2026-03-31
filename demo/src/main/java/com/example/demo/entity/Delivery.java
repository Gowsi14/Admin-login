package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import com.example.demo.entity.DeliveryStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "delivery")
@NoArgsConstructor
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String phone;
    private String pincode;
    private String address;
    private LocalDateTime deliveryTime;         //    private LocalDateTime deliveryTime;
    

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;   
}