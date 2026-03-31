package com.example.demo.dto;

public class DeliverySummary {

    private long total;
    private long delivered;
    private long pending;
    private long cancelled;

    public DeliverySummary(long total, long delivered, long pending, long cancelled) {
        this.total = total;
        this.delivered = delivered;
        this.pending = pending;
        this.cancelled = cancelled;
    }

    // Getters
    public long getTotal() { return total; }
    public long getDelivered() { return delivered; }
    public long getPending() { return pending; }
    public long getCancelled() { return cancelled; }

    // Setters
    public void setTotal(long total) { this.total = total; }
    public void setDelivered(long delivered) { this.delivered = delivered; }
    public void setPending(long pending) { this.pending = pending; }
    public void setCancelled(long cancelled) { this.cancelled = cancelled; }
}