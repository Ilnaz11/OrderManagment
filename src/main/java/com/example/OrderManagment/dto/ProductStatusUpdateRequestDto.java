package com.example.OrderManagment.dto;

import com.example.OrderManagment.Entity.ProductStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductStatusUpdateRequestDto {
    @NotNull(message = "Статус товара не может быть пустым")
    private ProductStatus productStatus;
}
