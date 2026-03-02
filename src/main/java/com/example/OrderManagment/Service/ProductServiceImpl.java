package com.example.OrderManagment.Service;

import com.example.OrderManagment.Entity.Product;
import com.example.OrderManagment.Entity.ProductStatus;
import com.example.OrderManagment.Exception.BusinessException;
import com.example.OrderManagment.Exception.ProductNotFoundException;
import com.example.OrderManagment.Repository.OrderRepository;
import com.example.OrderManagment.Repository.ProductRepository;
import com.example.OrderManagment.dto.*;
import com.example.OrderManagment.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, OrderRepository orderRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.productMapper = productMapper;
    }


    @Override
    public ProductResponseDto createProduct(CreateProductRequestDto productRequestDto) {
        // Тут можно добавить чтобы
        // только менеджер мог создавать товар
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
    public ProductResponseDto changeStatusProduct(Long id, ProductStatusUpdateRequestDto productRequestDto) {
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
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found: " + id);
        }
        if (orderRepository.existsByOrderItemsProductId(id)) {
            throw new BusinessException("You cannot delete the product contained in the order");
        }
        productRepository.deleteById(id);

    }

    @Override
    public List<ProductResponseDto> getProductsFromStatus(ProductStatus productStatus) {
        List<Product> products = productRepository.findByProductStatus(productStatus);
        return productMapper.toDtoList(products);
    }

    @Override
    public List<ProductCountAnalyticsDto> getBestSellers() {
        return productRepository.findBestSellers();
    }
}
