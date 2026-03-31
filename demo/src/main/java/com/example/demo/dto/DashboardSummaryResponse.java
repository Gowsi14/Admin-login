package com.example.demo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardSummaryResponse {

    private long ordersToday;
    private double revenueToday;
    private long activeSubscriptions;
    private double deliverySuccessRate;
    private long totalOrders;
    private double totalRevenue;
    private long totalDeliveries;
}