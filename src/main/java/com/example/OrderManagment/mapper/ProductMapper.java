package com.example.OrderManagment.mapper;

import com.example.OrderManagment.Entity.Product;
import com.example.OrderManagment.dto.ProductResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponseDto toDto(Product product) {
        if (product == null) {
            return null;
        }

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.
    }
}
