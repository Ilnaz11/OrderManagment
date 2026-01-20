package com.example.OrderManagment.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequestDto {
    private Long userId;
    private List<CreateOrderItemRequestDto> items;
}
