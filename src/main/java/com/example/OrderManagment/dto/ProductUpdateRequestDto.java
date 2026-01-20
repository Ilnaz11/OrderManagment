package com.example.OrderManagment.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequestDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
}