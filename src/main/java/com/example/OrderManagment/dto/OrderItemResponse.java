package com.example.OrderManagment.dto;

import com.example.OrderManagment.Entity.OrderItemStatus;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponse {
    private Long id;
    private String nameProduct;
    private Long quantity;
    private BigDecimal priceAtOrderTime;
    private OrderItemStatus status;
}