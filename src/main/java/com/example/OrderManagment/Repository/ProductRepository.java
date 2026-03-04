package com.example.OrderManagment.Repository;

import com.example.OrderManagment.Entity.OrderItemStatus;
import com.example.OrderManagment.Entity.OrderStatus;
import com.example.OrderManagment.Entity.Product;
import com.example.OrderManagment.Entity.ProductStatus;
import com.example.OrderManagment.dto.ProductCountAnalyticsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductStatus(ProductStatus productStatus);


    @Query("""
    select new com.example.OrderManagment.dto.ProductCountAnalyticsDto(
        p.id,
        p.name,
        sum(oi.quantity)
    )
    from OrderItem oi
    join oi.product p
    join oi.order o
    where o.currentStatus = :orderStatus
      and oi.status = :itemStatus
    group by p.id, p.name
    order by sum(oi.quantity) desc
""")
    List<ProductCountAnalyticsDto> findBestSellers(
            @Param("orderStatus") OrderStatus orderStatus,
            @Param("itemStatus") OrderItemStatus itemStatus
    );

}