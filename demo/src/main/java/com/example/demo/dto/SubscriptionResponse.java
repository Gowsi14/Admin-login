package com.example.demo.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionResponse {

    private Long customerId;
    private String name;
    private String phone;
    private String plan;
    private LocalDate startDate;
    private LocalDate renewalDate;
    private long daysLeft;
    private String status;
}