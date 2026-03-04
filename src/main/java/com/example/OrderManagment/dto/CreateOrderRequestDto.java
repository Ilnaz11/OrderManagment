package com.example.OrderManagment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequestDto {
    @NotNull(message = "ID пользователя не может быть пустым")
    private Long userId;
    @NotEmpty(message = "Список заказа позиций не может быть пустым")
    private List<CreateOrderItemRequestDto> items;
}
