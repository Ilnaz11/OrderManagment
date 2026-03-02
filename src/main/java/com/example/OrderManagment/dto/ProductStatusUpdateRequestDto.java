package com.example.OrderManagment.dto;

import com.example.OrderManagment.Entity.ProductStatus;
import lombok.Data;

@Data
public class ProductStatusUpdateRequestDto {
    private ProductStatus productStatus;
}
