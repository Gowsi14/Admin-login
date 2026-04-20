package com.example.demo.rest;

import com.example.demo.dto.*;
import com.example.demo.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/summary")
    public ResponseEntity<DashboardSummaryResponse> getSummary() {
        return ResponseEntity.ok(dashboardService.getDashboardSummary());
    }

    @GetMapping("/today-orders")
    public ResponseEntity<List<TodayOrderResponse>> getTodayOrders() {
        return ResponseEntity.ok(dashboardService.getTodaysOrders());
    }

    @GetMapping("/active-subscriptions")
    public ResponseEntity<List<SubscriptionResponse>> getActiveSubscriptions() {
        return ResponseEntity.ok(dashboardService.getActiveSubscriptions());
    }

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

    @GetMapping("/analytics/monthly-revenue")
    public ResponseEntity<List<Double>> getMonthlyRevenueChart() {
        return ResponseEntity.ok(dashboardService.getMonthlyRevenueChart());
    }

    @GetMapping("/analytics/daily-orders")
    public ResponseEntity<List<Long>> getDailyOrders() {
        return ResponseEntity.ok(dashboardService.getDailyOrdersChart());
    }
}