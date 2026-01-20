package com.example.OrderManagment.Service;

import com.example.OrderManagment.Entity.Product;
import com.example.OrderManagment.Entity.ProductStatus;
import com.example.OrderManagment.dto.CreateProductRequestDto;
import com.example.OrderManagment.dto.ProductResponseDto;
import com.example.OrderManagment.dto.ProductStatusUpdateRequest;
import com.example.OrderManagment.dto.ProductUpdateRequestDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(CreateProductRequestDto productRequestDto);
    ProductResponseDto updateProduct(Long id, ProductUpdateRequestDto productRequestDto);
    ProductResponseDto changeStatusProduct(Long id, ProductStatusUpdateRequest productRequestDto);
    List<ProductResponseDto> getAllProducts();
    void deleteProduct(Long id);
    List<ProductResponseDto> getProductsFromStatus(ProductStatus productStatus);
}