package com.example.demo.dto;

public class DashboardSummaryResponse {

    private long ordersToday;
    private double revenueToday;
    private long activeSubscriptions;
    private double deliveryStatusPercent;

    public DashboardSummaryResponse() {}

    public DashboardSummaryResponse(long ordersToday,
                                    double revenueToday,
                                    long activeSubscriptions,
                                    double deliveryStatusPercent) {
        this.ordersToday = ordersToday;
        this.revenueToday = revenueToday;
        this.activeSubscriptions = activeSubscriptions;
        this.deliveryStatusPercent = deliveryStatusPercent;
    }

    public long getOrdersToday() {
        return ordersToday;
    }

    public void setOrdersToday(long ordersToday) {
        this.ordersToday = ordersToday;
    }

    public double getRevenueToday() {
        return revenueToday;
    }

    public void setRevenueToday(double revenueToday) {
        this.revenueToday = revenueToday;
    }

    public long getActiveSubscriptions() {
        return activeSubscriptions;
    }

    public void setActiveSubscriptions(long activeSubscriptions) {
        this.activeSubscriptions = activeSubscriptions;
    }

    public double getDeliveryStatusPercent() {
        return deliveryStatusPercent;
    }

    public void setDeliveryStatusPercent(double deliveryStatusPercent) {
        this.deliveryStatusPercent = deliveryStatusPercent;
    }
}