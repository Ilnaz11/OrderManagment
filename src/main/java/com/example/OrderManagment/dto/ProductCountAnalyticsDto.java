package com.example.OrderManagment.dto;

import lombok.Data;

@Data
public class ProductCountAnalyticsDto {
    Long productId;
    String productName;
    Integer countSales;
}
