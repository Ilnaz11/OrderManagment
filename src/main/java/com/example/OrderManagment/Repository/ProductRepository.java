package com.example.OrderManagment.Repository;

import com.example.OrderManagment.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
