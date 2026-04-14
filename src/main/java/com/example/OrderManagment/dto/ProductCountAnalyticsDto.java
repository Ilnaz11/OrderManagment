package com.example.OrderManagment.dto;

public class ProductCountAnalyticsDto {

    private Long productId;
    private String productName;
    private Long totalQuantity;

    public ProductCountAnalyticsDto(
            Long productId,
            String productName,
            Long totalQuantity
    ) {
        this.productId = productId;
        this.productName = productName;
        this.totalQuantity = totalQuantity;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }
}
