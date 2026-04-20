package com.example.demo.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private Long customerId;
    private String name;
    private String phone;
    private String area;
    private String pincode;
    private String district;
    private String zone;
    private String plan;
    private LocalDate lastOrder;
    private String status;
}