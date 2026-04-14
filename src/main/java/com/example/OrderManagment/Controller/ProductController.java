package com.example.OrderManagment.Controller;

import com.example.OrderManagment.Entity.ProductStatus;
import com.example.OrderManagment.Service.ProductService;
import com.example.OrderManagment.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Управление товарами")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductResponseDto createProduct(@RequestBody @Valid CreateProductRequestDto productRequestDto) {
        return productService.createProduct(productRequestDto);
    }

    @PutMapping("/update/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody @Valid ProductUpdateRequestDto productUpdateRequestDto) {
        return productService.updateProduct(id, productUpdateRequestDto);
    }

    @PatchMapping("/update/status/{id}")
    public ProductResponseDto changeStatusProduct(@PathVariable Long id, @RequestBody @Valid ProductStatusUpdateRequestDto productStatusUpdateRequestDto) {
        return productService.changeStatusProduct(id, productStatusUpdateRequestDto);
    }

    @GetMapping
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

    @Operation(summary = "Самые продаваемые товары")
    @GetMapping("/analytics")
    public List<ProductCountAnalyticsDto> getBestSellers() {
        return productService.getBestSellers();
    }

}