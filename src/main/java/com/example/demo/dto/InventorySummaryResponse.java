package com.example.demo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventorySummaryResponse {
    private long totalItems;
    private long lowStockItems;
    private long outOfStock;
}