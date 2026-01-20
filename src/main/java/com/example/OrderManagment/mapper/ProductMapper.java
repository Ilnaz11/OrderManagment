package com.example.OrderManagment.mapper;

import com.example.OrderManagment.Entity.Product;
import com.example.OrderManagment.dto.CreateProductRequestDto;
import com.example.OrderManagment.dto.ProductResponseDto;
import com.example.OrderManagment.dto.ProductStatusUpdateRequest;
import com.example.OrderManagment.dto.ProductUpdateRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public ProductResponseDto toDto(Product product) {
        if (product == null) {
            return null;
        }

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setQuantity(product.getQuantity());
        productResponseDto.setProductStatus(product.getProductStatus());

        return productResponseDto;
    }

    public Product toEntity(CreateProductRequestDto productRequestDto) {

        if (productRequestDto == null) {
            return null;
        }

        Product product = new Product();

        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(productRequestDto.getQuantity());
        product.setProductStatus(productRequestDto.getProductStatus());

        return product;
    }

    public List<ProductResponseDto> toDtoList(List<Product> products) {
        return products.
                stream()
                .map(this::toDto)
                .toList();
    }

    public void updateEntityFromDto(ProductUpdateRequestDto dto, Product product) {
        if (dto == null || product == null) {
            return ;
        }
        if (dto.getName() != null) {
            product.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            product.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null) {
            product.setPrice(dto.getPrice());
        }
        if (dto.getQuantity() != null) {
            product.setQuantity(dto.getQuantity());
        }

    }

    public void updateEntityStatusFromDto(ProductStatusUpdateRequest dto, Product product) {
        if (dto == null || product == null) {
            return ;
        }
        if (dto.getProductStatus() != null) {
            product.setProductStatus(dto.getProductStatus());
        }
    }
}