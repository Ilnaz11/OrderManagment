package com.example.OrderManagment.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderHistory")
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "updateDate")
    private LocalDateTime updateDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "oldStatus")
    private OrderStatus oldStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "newStatus")
    private OrderStatus newStatus;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}