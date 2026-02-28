package com.example.OrderManagment.Controller;

import com.example.OrderManagment.Entity.ProductStatus;
import com.example.OrderManagment.Service.ProductService;
import com.example.OrderManagment.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductResponseDto createProduct(@RequestBody CreateProductRequestDto productRequestDto) {
        return productService.createProduct(productRequestDto);
    }

    @PutMapping("/update/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequestDto productUpdateRequestDto) {
        return productService.updateProduct(id, productUpdateRequestDto);
    }

    @PatchMapping("/update/status/{id}")
    public ProductResponseDto changeStatusProduct(@PathVariable Long id, @RequestBody ProductStatusUpdateRequestDto productStatusUpdateRequestDto) {
        return productService.changeStatusProduct(id, productStatusUpdateRequestDto);
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/status/{productStatus}")
    List<ProductResponseDto> getProductsFromStatus(@PathVariable ProductStatus productStatus) {
        return productService.getProductsFromStatus(productStatus);
    }

    @GetMapping("/analytics")
    public List<ProductCountAnalyticsDto> getBestSellers() {
        return productService.getBestSellers();
    }

}