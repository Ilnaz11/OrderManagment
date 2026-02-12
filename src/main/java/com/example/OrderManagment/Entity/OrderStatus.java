package com.example.OrderManagment.Entity;

public enum OrderStatus {
    CREATED, // Создан - Оплачен - Подтвержден - Отправлен - Доставлен - Отменен
    PAID,
    CONFIRMED,
    SHIPPED,
    DELIVERED,
    CANCELLED
}
