package com.example.demo.rest;

import com.example.demo.dto.DashboardSummaryResponse;
import com.example.demo.dto.DeliverySummary;
import com.example.demo.entity.Order;
import com.example.demo.entity.Subscription;
import com.example.demo.entity.Delivery;
import com.example.demo.service.DashboardService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

        // ✅ Constructor Injection (NO Lombok)
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // ✅ Dashboard Summary
    @GetMapping("/summary")
    public ResponseEntity<DashboardSummaryResponse> getSummary() {
        return ResponseEntity.ok(dashboardService.getDashboardSummary());
    }

    // ✅ Today's Orders
    @GetMapping("/today-orders")
    public ResponseEntity<List<Order>> getTodayOrders() {
        return ResponseEntity.ok(dashboardService.getTodaysOrders());
    }

    // ✅ Active Subscriptions
    @GetMapping("/active-subscriptions")
    public ResponseEntity<List<Subscription>> getActiveSubscriptions() {
        return ResponseEntity.ok(dashboardService.getActiveSubscriptions());
    }

    // 💰 Revenue APIs
    @GetMapping("/revenue/today")
    public ResponseEntity<Double> getTodayRevenue() {
        return ResponseEntity.ok(dashboardService.getTodayRevenue());
    }

    @GetMapping("/revenue/week")
    public ResponseEntity<Double> getWeeklyRevenue() {
        return ResponseEntity.ok(dashboardService.getWeeklyRevenue());
    }

    @GetMapping("/revenue/month")
    public ResponseEntity<Double> getMonthlyRevenue() {
        return ResponseEntity.ok(dashboardService.getMonthlyRevenue());
    }

    @GetMapping("/revenue/year")
    public ResponseEntity<Double> getYearlyRevenue() {
        return ResponseEntity.ok(dashboardService.getYearlyRevenue());
    }

    // 🚚 Delivery APIs
    @GetMapping("/delivery/today")
    public ResponseEntity<DeliverySummary> getTodayDelivery() {
        return ResponseEntity.ok(dashboardService.getTodayDeliveryStatus());
    }

    @GetMapping("/delivery/completed")
    public ResponseEntity<Long> getCompleted() {
        return ResponseEntity.ok(dashboardService.getCompletedDeliveries());
    }

    @GetMapping("/delivery/pending")
    public ResponseEntity<Long> getPending() {
        return ResponseEntity.ok(dashboardService.getPendingDeliveries());
    }

    @GetMapping("/delivery/out-for-delivery")
    public ResponseEntity<Long> getOutForDelivery() {
        return ResponseEntity.ok(dashboardService.getOutForDelivery());
    }
}