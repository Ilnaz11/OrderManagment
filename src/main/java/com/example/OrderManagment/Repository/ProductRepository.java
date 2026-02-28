package com.example.OrderManagment.Repository;

import com.example.OrderManagment.Entity.Product;
import com.example.OrderManagment.Entity.ProductStatus;
import com.example.OrderManagment.dto.ProductCountAnalyticsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStatus(ProductStatus productStatus);


    @Query("""
            select new com.example.OrderManagement.dto.ProductCountAnalyticsDto(
            p.id,
            p.name,
            sum(oi.quantity)) from OrderItem oi join oi.product p join oi.order o
            where o.currentStatus = com.example.OrderManagement.Entity.OrderStatus.DELIVERED
            and oi.status = com.example.OrderManagement.Entity.OrderItemStatus.ACTIVE
            group by p.id, p.name
            order by sum(oi.quantity) desc
            """) // Тут получаем сгруппированный список самых продаваемых товаров
    List<ProductCountAnalyticsDto> findBestSellers();
}