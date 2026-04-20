package com.example.demo.service;

import com.example.demo.dto.DashboardSummaryResponse;
import com.example.demo.dto.DeliverySummary;
import com.example.demo.dto.TodayOrderResponse;
import com.example.demo.dto.SubscriptionResponse;
import com.example.demo.entity.DeliveryStatus;
import com.example.demo.repo.OrderRepository;
import com.example.demo.repo.SubscriptionRepository;
import com.example.demo.repo.DeliveryRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final OrderRepository orderRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final DeliveryRepository deliveryRepository;

    public DashboardService(OrderRepository orderRepository,
                            SubscriptionRepository subscriptionRepository,
                            DeliveryRepository deliveryRepository) {
        this.orderRepository = orderRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.deliveryRepository = deliveryRepository;
    }

    // =========================
    // COMMON DATE RANGE
    // =========================
    private LocalDateTime[] getTodayRange() {
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay(); // open-end (< end)
        return new LocalDateTime[]{start, end};
    }

    // =========================
    // DASHBOARD SUMMARY
    // =========================
    public DashboardSummaryResponse getDashboardSummary() {
        LocalDateTime[] range = getTodayRange();

        long ordersToday = orderRepository.countByOrderTimeBetween(range[0], range[1]);

        Double revenueToday = orderRepository.sumAmountByOrderTimeBetween(range[0], range[1]);
        double revenue = (revenueToday != null) ? revenueToday : 0;

        long activeSubs = subscriptionRepository.countByStatus("ACTIVE");

        long totalDeliveries = deliveryRepository.countByDeliveryDate(range[0], range[1]);

        long delivered = deliveryRepository.countByDeliveryDateAndStatus(
                range[0], range[1], DeliveryStatus.DELIVERED);

        double percent = (totalDeliveries == 0) ? 0 : (delivered * 100.0) / totalDeliveries;

        return new DashboardSummaryResponse(ordersToday, revenue, activeSubs, percent);
    }

    // =========================
    // TODAY ORDERS → DTO
    // =========================
    public List<TodayOrderResponse> getTodaysOrders() {
        LocalDateTime[] range = getTodayRange();

        return orderRepository.findByOrderTimeBetween(range[0], range[1])
                .stream()
                .map(o -> TodayOrderResponse.builder()
                        .orderId(o.getId())
                        .customer(o.getCustomerName())
                        .phone(o.getPhone())
                        .plan(o.getPlan())
                        .orderTime(o.getOrderTime())
                        .status(o.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    // =========================
    // ACTIVE SUBSCRIPTIONS → DTO
    // =========================
public List<SubscriptionResponse> getActiveSubscriptions() {
    LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));

    return subscriptionRepository.findByStatus("ACTIVE")
            .stream()
            .map(s -> {
                long daysLeft = (s.getRenewalDate() != null)
                        ? ChronoUnit.DAYS.between(today, s.getRenewalDate())
                        : 0L;

                return SubscriptionResponse.builder()
                        .customerId(s.getId())
                        .name(s.getCustomerName())
                        .phone(s.getPhone())
                        .plan(s.getPlan())
                        .startDate(s.getStartDate())
                        .renewalDate(s.getRenewalDate())
                        .daysLeft(daysLeft)
                        .status(s.getStatus())
                        .build();
            })
            .collect(Collectors.toList());
}

    // =========================
    // REVENUE
    // =========================
    public double getTodayRevenue() {
        LocalDateTime[] range = getTodayRange();
        Double revenue = orderRepository.sumAmountByOrderTimeBetween(range[0], range[1]);
        return (revenue != null) ? revenue : 0;
    }

    public double getWeeklyRevenue() {
        LocalDateTime[] range = getTodayRange();
        LocalDateTime start = range[0].minusDays(6);
        Double revenue = orderRepository.sumAmountByOrderTimeBetween(start, range[1]);
        return (revenue != null) ? revenue : 0;
    }

    public double getMonthlyRevenue() {
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        Double revenue = orderRepository.sumAmountByOrderTimeBetween(
                today.withDayOfMonth(1).atStartOfDay(),
                getTodayRange()[1]);
        return (revenue != null) ? revenue : 0;
    }

    public double getYearlyRevenue() {
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        Double revenue = orderRepository.sumAmountByOrderTimeBetween(
                today.withDayOfYear(1).atStartOfDay(),
                getTodayRange()[1]);
        return (revenue != null) ? revenue : 0;
    }

    // =========================
    // DELIVERY SUMMARY
    // =========================
    public DeliverySummary getTodayDeliveryStatus() {
        LocalDateTime[] range = getTodayRange();

        long total     = deliveryRepository.countByDeliveryDate(range[0], range[1]);
        long delivered = deliveryRepository.countByDeliveryDateAndStatus(range[0], range[1], DeliveryStatus.DELIVERED);
        long pending   = deliveryRepository.countByDeliveryDateAndStatus(range[0], range[1], DeliveryStatus.PENDING);
        long cancelled = deliveryRepository.countByDeliveryDateAndStatus(range[0], range[1], DeliveryStatus.CANCELLED);

        return new DeliverySummary(total, delivered, pending, cancelled);
    }

    public long getCompletedDeliveries() {
        LocalDateTime[] range = getTodayRange();
        return deliveryRepository.countByDeliveryDateAndStatus(range[0], range[1], DeliveryStatus.DELIVERED);
    }

    public long getPendingDeliveries() {
        LocalDateTime[] range = getTodayRange();
        return deliveryRepository.countByDeliveryDateAndStatus(range[0], range[1], DeliveryStatus.PENDING);
    }

    public long getOutForDelivery() {
        LocalDateTime[] range = getTodayRange();
        return deliveryRepository.countByDeliveryDateAndStatus(range[0], range[1], DeliveryStatus.OUT_FOR_DELIVERY);
    }

    // =========================
    // ANALYTICS
    // =========================
    public List<Double> getMonthlyRevenueChart() {
        int year = LocalDate.now(ZoneId.of("Asia/Kolkata")).getYear();

        return java.util.stream.IntStream.rangeClosed(1, 12)
                .mapToObj(month -> {
                    LocalDate start = LocalDate.of(year, month, 1);
                    LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
                    Double revenue = orderRepository.sumAmountByOrderTimeBetween(
                            start.atStartOfDay(),
                            end.plusDays(1).atStartOfDay());
                    return (revenue != null) ? revenue : 0.0;
                })
                .collect(Collectors.toList());
    }

    public List<Long> getDailyOrdersChart() {
        LocalDate monday = LocalDate.now(ZoneId.of("Asia/Kolkata"))
                .with(java.time.DayOfWeek.MONDAY);

        return java.util.stream.IntStream.range(0, 7)
                .mapToObj(i -> {
                    LocalDate day = monday.plusDays(i);
                    return orderRepository.countByOrderTimeBetween(
                            day.atStartOfDay(),
                            day.plusDays(1).atStartOfDay());
                })
                .collect(Collectors.toList());
    }
}