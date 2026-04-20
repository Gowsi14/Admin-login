package com.example.demo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerSummaryResponse {
    private long totalCustomers;
    private long activeSubscriptions;
    private long purchasedToday;
    private long inactiveCustomers;
}