package com.example.OrderManagment.dto;

import com.example.OrderManagment.Entity.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDto {
    private Long id;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime lastChange;
    private OrderStatus currentStatus;
    private List<OrderItemResponse> items;
}
