package com.example.OrderManagment.dto;

import com.example.OrderManagment.Entity.ProductStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private ProductStatus productStatus;
}