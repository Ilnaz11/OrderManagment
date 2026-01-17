package com.example.OrderManagment.dto;

import java.util.List;

public class CreateOrderRequestDto {
    private Long userId;
    private List<CreateOrderItemRequestDto> items;
}
