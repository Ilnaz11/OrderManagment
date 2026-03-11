package com.example.OrderManagment.dto;


import com.example.OrderManagment.Entity.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductRequestDto {
    @NotBlank(message = "Название товара не может быть пустым")
    private String name;
    private String description;
    @NotNull(message = "Цена товара не должна быть пустой")
    private BigDecimal price;
    private Integer quantity;
    @NotNull(message = "Статус товара не может быть пустым")
    private ProductStatus productStatus;
}