package com.example.demo.dto;

public class DeliverySummary {

    private long total;
    private long delivered;
    private long pending;
    private long cancelled;

    public DeliverySummary() {
    }

    public DeliverySummary(long total, long delivered, long pending, long cancelled) {
        this.total = total;
        this.delivered = delivered;
        this.pending = pending;
        this.cancelled = cancelled;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getDelivered() {
        return delivered;
    }

    public void setDelivered(long delivered) {
        this.delivered = delivered;
    }

    public long getPending() {
        return pending;
    }

    public void setPending(long pending) {
        this.pending = pending;
    }

    public long getCancelled() {
        return cancelled;
    }

    public void setCancelled(long cancelled) {
        this.cancelled = cancelled;
    }
}