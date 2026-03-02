package com.example.OrderManagment.Repository;

import com.example.OrderManagment.Entity.Order;
import com.example.OrderManagment.Entity.OrderStatus;
import com.example.OrderManagment.dto.OrderStatusCountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    boolean existsByOrderItem_Product_Id(Long id);

    @Query("""
        select new com.example.OrderManagement.dto.OrderStatusCountDto(
            o.currentStatus,
            count(o)
        )
        from Order o
        group by o.currentStatus
    """) // Тут считает кол-во заказов по каждому статусу
    List<OrderStatusCountDto> countOrderByStatus();

    @Query("""
            selet coalesce(sum(o.totalPrice), 0)
            from Order o
            where o.currentStatus = : status
            """) // Возвращает общую сумму завершенных заказов
    BigDecimal sumTotalPriceByStatus(@Param("status") OrderStatus orderStatus);

}