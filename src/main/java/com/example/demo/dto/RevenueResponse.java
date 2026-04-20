package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RevenueResponse {
    private double today;
    private double week;
    private double month;
    private double year;
    private double avgOrderValue;
    private long totalOrders;
    private long successPayments;
    private long failedPayments;
}