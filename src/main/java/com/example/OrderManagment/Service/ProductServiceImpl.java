package com.example.OrderManagment.Service;

import com.example.OrderManagment.Entity.Product;
import com.example.OrderManagment.Entity.ProductStatus;
import com.example.OrderManagment.Repository.ProductRepository;
import com.example.OrderManagment.dto.CreateProductRequestDto;
import com.example.OrderManagment.dto.ProductResponseDto;
import com.example.OrderManagment.dto.ProductStatusUpdateRequest;
import com.example.OrderManagment.dto.ProductUpdateRequestDto;
import com.example.OrderManagment.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    @Override
    public ProductResponseDto createProduct(CreateProductRequestDto productRequestDto) {
        Product product = productMapper.toEntity(productRequestDto);
        Product product1 = productRepository.save(product);
        return productMapper.toDto(product1);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductUpdateRequestDto productRequestDto) {
        Optional<Product> productOptional = productRepository.findById(id);

        Product productToUpdate = productOptional
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        productMapper.updateEntityFromDto(productRequestDto, productToUpdate);

        return productMapper.toDto(productToUpdate);
    }

    @Override
    public ProductResponseDto changeStatusProduct(Long id, ProductStatusUpdateRequest productRequestDto) {
        Optional<Product> productOptional = productRepository.findById(id);

        Product productToUpdateStatus = productOptional
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        productMapper.updateEntityStatusFromDto(productRequestDto, productToUpdateStatus);

        return productMapper.toDto(productToUpdateStatus);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toDtoList(products);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDto> getProductsFromStatus(ProductStatus productStatus) {
        List<Product> products = productRepository.findByStatus(productStatus);
        return productMapper.toDtoList(products);
    }
}
