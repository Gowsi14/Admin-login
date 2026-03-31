package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "subscriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String phone;
    private String plan;
    private String status;

    private LocalDate startDate;
    private LocalDate renewalDate;
}