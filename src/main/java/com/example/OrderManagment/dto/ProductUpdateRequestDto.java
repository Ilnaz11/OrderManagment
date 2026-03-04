package com.example.OrderManagment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequestDto {
    @NotBlank(message = "Название товара не может быть пустым")
    private String name;
    private String description;
    @NotNull(message = "Цена товара не должна быть пустой")
    private BigDecimal price;
    @NotNull(message = "Количество товара не может быть пустым или равным 0")
    private Integer quantity;
}