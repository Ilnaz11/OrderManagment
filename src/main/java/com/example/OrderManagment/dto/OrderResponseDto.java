package com.example.OrderManagment.dto;

import com.example.OrderManagment.Entity.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDto {
    private Long id;
    private BigDecimal totalPrice;
    private OrderStatus currentStatus;
    private LocalDateTime createdAt;
    private LocalDateTime lastChange;
    private OrderStatus orderStatus;
    private List<OrderItemResponse> items;
}
