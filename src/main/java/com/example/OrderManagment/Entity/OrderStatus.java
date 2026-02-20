package com.example.OrderManagment.Entity;

import java.util.EnumSet;
import java.util.Set;

public enum OrderStatus {
    CREATED,
    PAID,
    CONFIRMED,
    SHIPPED,
    DELIVERED,
    CANCELLED;

    private Set<OrderStatus> allowedTransitions;


    static {
        CREATED.allowedTransitions = EnumSet.of(PAID, CANCELLED);
        PAID.allowedTransitions = EnumSet.of(CONFIRMED, CANCELLED);
        CONFIRMED.allowedTransitions = EnumSet.of(SHIPPED, CANCELLED);
        SHIPPED.allowedTransitions = EnumSet.of(DELIVERED, CANCELLED);
        DELIVERED.allowedTransitions = EnumSet.noneOf(OrderStatus.class);
        CANCELLED.allowedTransitions = EnumSet.noneOf(OrderStatus.class);
    }

    public boolean canTransitionTo(OrderStatus targetStatus) {
        return allowedTransitions.contains(targetStatus);
    }

}
