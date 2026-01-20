package com.example.OrderManagment.dto;

import com.example.OrderManagment.Entity.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderHistoryResponseDto {
    private Long id;
    private LocalDateTime updateDate;
    private OrderStatus oldStatus;
    private OrderStatus newStatus;
    private String comment;
}