package com.example.OrderManagment.dto;

import lombok.Data;

@Data
public class CreateOrderItemRequestDto {
    private Long productId;
    private Integer quantity;
}

