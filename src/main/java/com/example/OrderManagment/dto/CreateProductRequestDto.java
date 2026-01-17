package com.example.OrderManagment.dto;

import com.example.OrderManagment.Entity.ProductStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductRequestDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Long quantity;
}