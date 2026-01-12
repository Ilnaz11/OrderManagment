package com.example.OrderManagment.Repository;

import com.example.OrderManagment.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
