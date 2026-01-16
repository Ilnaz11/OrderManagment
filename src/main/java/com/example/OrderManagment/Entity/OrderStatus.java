package com.example.OrderManagment.Entity;

public enum OrderStatus {
    CREATED, // Создан - Оплачен - Подтвержден - отправлен - доставлен - отменен
    PAID,
    CONFIRMED,
    SHIPPED,
    DELIVERED,
    CANCELLED
}
