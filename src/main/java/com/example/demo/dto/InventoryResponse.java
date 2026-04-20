package com.example.demo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {
    private Long id;
    private String itemName;
    private String category;
    private int quantity;
    private String unit;
    private double price;
    private String status;
}