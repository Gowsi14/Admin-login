package com.example.demo.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodayOrderResponse {
    private Long orderId;
    private String customer;
    private String phone;
    private String plan;
    private LocalDateTime orderTime;
    private String status;
}