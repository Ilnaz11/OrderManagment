package com.example.OrderManagment.Entity;

public enum ProductStatus {
    ACTIVE, // Указываем что товар доступен на складе
    OUT_OF_STOCK, // Нету на складе
    DISABLED // Временно недоступно
}