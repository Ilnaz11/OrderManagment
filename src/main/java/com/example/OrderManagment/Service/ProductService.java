package com.example.OrderManagment.Service;

import com.example.OrderManagment.Entity.ProductStatus;
import com.example.OrderManagment.dto.*;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(CreateProductRequestDto productRequestDto);
    ProductResponseDto updateProduct(Long id, ProductUpdateRequestDto productRequestDto);
    ProductResponseDto changeStatusProduct(Long id, ProductStatusUpdateRequestDto productRequestDto);
    List<ProductResponseDto> getAllProducts();
    void deleteProduct(Long id);
    List<ProductResponseDto> getProductsFromStatus(ProductStatus productStatus);
    List<ProductCountAnalyticsDto> getBestSellers();
}