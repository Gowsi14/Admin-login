package com.example.demo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerAreaResponse {
    private String area;
    private long totalCustomers;
    private long activeSubscriptions;
}