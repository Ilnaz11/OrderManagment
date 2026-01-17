package com.example.OrderManagment.Service;

import com.example.OrderManagment.Entity.Product;
import com.example.OrderManagment.Entity.ProductStatus;
import com.example.OrderManagment.Repository.ProductRepository;
import com.example.OrderManagment.dto.CreateProductRequestDto;
import com.example.OrderManagment.dto.ProductResponseDto;
import com.example.OrderManagment.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }


    @Override
    public ProductResponseDto createProduct(Product product) {
        Product product1 = productRepository.save(product);
        return
    }

    @Override
    public ProductResponseDto updateProduct(Long id, CreateProductRequestDto productRequestDto) {
        return null;
    }

    @Override
    public ProductResponseDto changeStatusProduct(Long id, CreateProductRequestDto productRequestDto) {
        return null;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return List.of();
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public List<ProductResponseDto> getProductsFromStatus(ProductStatus productStatus) {
        return List.of();
    }
}
