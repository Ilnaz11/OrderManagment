package com.example.OrderManagment.Service;

import com.example.OrderManagment.Entity.Product;
import com.example.OrderManagment.Entity.ProductStatus;
import com.example.OrderManagment.dto.CreateProductRequestDto;
import com.example.OrderManagment.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(Product product);
    ProductResponseDto updateProduct(Long id, CreateProductRequestDto productRequestDto);
    ProductResponseDto changeStatusProduct(Long id, CreateProductRequestDto productRequestDto);
    List<ProductResponseDto> getAllProducts();
    void deleteProduct(Long id);
    List<ProductResponseDto> getProductsFromStatus(ProductStatus productStatus);
}
