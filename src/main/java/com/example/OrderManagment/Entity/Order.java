package com.example.OrderManagment.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long totalPrice;
    @Enumerated(EnumType.STRING)
    private OrderStatus currentStatus;
    private LocalDateTime createdAt;
    private LocalDateTime lastChange;

    private List<OrderItem> orderItems;

    private User user;
}
