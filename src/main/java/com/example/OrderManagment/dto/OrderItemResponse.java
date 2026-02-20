package com.example.OrderManagment.dto;

import com.example.OrderManagment.Entity.OrderItemStatus;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponse {
    private Long id;
    private Long productId;
    private Integer quantity;
    private BigDecimal priceAtOrderTime;
    private OrderItemStatus status;
}