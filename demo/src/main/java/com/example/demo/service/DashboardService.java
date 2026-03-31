package com.example.demo.service;

import com.example.demo.dto.DashboardSummaryResponse;
import com.example.demo.dto.DeliverySummary;
import com.example.demo.entity.Order;
import com.example.demo.entity.Subscription;
import com.example.demo.entity.DeliveryStatus;
import com.example.demo.repo.OrderRepository;
import com.example.demo.repo.SubscriptionRepository;
import com.example.demo.repo.DeliveryRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DashboardService {

    private final OrderRepository orderRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final DeliveryRepository deliveryRepository;

    // ✅ Constructor Injection
    public DashboardService(OrderRepository orderRepository,
                            SubscriptionRepository subscriptionRepository,
                            DeliveryRepository deliveryRepository) {
        this.orderRepository = orderRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.deliveryRepository = deliveryRepository;
    }

    // ✅ Dashboard Summary
    public DashboardSummaryResponse getDashboardSummary() {

        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.atTime(23, 59, 59);

        long ordersToday = orderRepository.countByOrderTimeBetween(start, end);

        Double revenueToday = orderRepository.sumAmountByOrderTimeBetween(start, end);
        revenueToday = (revenueToday == null) ? 0 : revenueToday;

        long activeSubs = subscriptionRepository.countByStatus("ACTIVE");

        long totalDeliveries = deliveryRepository.countByDeliveryDate(today);

        long delivered = deliveryRepository.countByDeliveryDateAndStatus(
                today, DeliveryStatus.DELIVERED
        );

        double percent = (totalDeliveries == 0)
                ? 0
                : (delivered * 100.0) / totalDeliveries;

        return new DashboardSummaryResponse(
                ordersToday,
                revenueToday,
                activeSubs,
                percent
        );
    }

    // ✅ Today's Orders
    public List<Order> getTodaysOrders() {
        LocalDate today = LocalDate.now();

        return orderRepository.findByOrderTimeBetween(
                today.atStartOfDay(),
                today.atTime(23, 59, 59)
        );
    }

    // ✅ Active Subscriptions
    public List<Subscription> getActiveSubscriptions() {
        return subscriptionRepository.findByStatus("ACTIVE");
    }

    // 💰 Revenue Methods
    public double getTodayRevenue() {
        LocalDate today = LocalDate.now();

        Double revenue = orderRepository.sumAmountByOrderTimeBetween(
                today.atStartOfDay(),
                today.atTime(23, 59, 59)
        );

        return revenue == null ? 0 : revenue;
    }

    public double getWeeklyRevenue() {
        LocalDate today = LocalDate.now();

        Double revenue = orderRepository.sumAmountByOrderTimeBetween(
                today.minusDays(6).atStartOfDay(),
                today.atTime(23, 59, 59)
        );

        return revenue == null ? 0 : revenue;
    }

    public double getMonthlyRevenue() {
        LocalDate today = LocalDate.now();

        Double revenue = orderRepository.sumAmountByOrderTimeBetween(
                today.withDayOfMonth(1).atStartOfDay(),
                today.atTime(23, 59, 59)
        );

        return revenue == null ? 0 : revenue;
    }

    public double getYearlyRevenue() {
        LocalDate today = LocalDate.now();

        Double revenue = orderRepository.sumAmountByOrderTimeBetween(
                today.withDayOfYear(1).atStartOfDay(),
                today.atTime(23, 59, 59)
        );

        return revenue == null ? 0 : revenue;
    }

    // 🚚 Delivery Summary
    public DeliverySummary getTodayDeliveryStatus() {

        LocalDate today = LocalDate.now();

        long total = deliveryRepository.countByDeliveryDate(today);
        long delivered = deliveryRepository.countByDeliveryDateAndStatus(today, DeliveryStatus.DELIVERED);
        long pending = deliveryRepository.countByDeliveryDateAndStatus(today, DeliveryStatus.PENDING);
        long cancelled = deliveryRepository.countByDeliveryDateAndStatus(today, DeliveryStatus.CANCELLED);

        return new DeliverySummary(total, delivered, pending, cancelled);
    }

    // ✅ Extra APIs (Controller uses these)
    public long getCompletedDeliveries() {
        return deliveryRepository.countByDeliveryDateAndStatus(
                LocalDate.now(), DeliveryStatus.DELIVERED
        );
    }

    public long getPendingDeliveries() {
        return deliveryRepository.countByDeliveryDateAndStatus(
                LocalDate.now(), DeliveryStatus.PENDING
        );
    }

    public long getOutForDelivery() {
        return deliveryRepository.countByDeliveryDateAndStatus(
                LocalDate.now(), DeliveryStatus.OUT_FOR_DELIVERY
        );
    }
}