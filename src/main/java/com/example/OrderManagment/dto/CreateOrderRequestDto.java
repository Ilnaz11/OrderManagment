package com.example.OrderManagment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequestDto {
    @NotBlank(message = "ID пользователя не может быть пустым")
    private Long userId;
    @NotBlank(message = "Список заказа позиций не может быть пустым")
    private List<CreateOrderItemRequestDto> items;
}
