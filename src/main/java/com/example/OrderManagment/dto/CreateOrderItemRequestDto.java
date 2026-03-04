package com.example.OrderManagment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderItemRequestDto {
    private Long productId;
    @NotNull(message = "Количество не может быть пустым или равным 0")
    private Integer quantity;
}

