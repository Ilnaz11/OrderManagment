package com.example.OrderManagment.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderItems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price_At_Order_Time", nullable = false)
    private BigDecimal priceAtOrderTime; // Цена товара на момент добавления в заказ
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderItemStatus status;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY) // в ManyToOne fetch всегда по умолчанию EAGER,но нам такое не нужно
    @JoinColumn(name = "order_id")
    private Order order;
}
