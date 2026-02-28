package com.example.OrderManagment.Service;

import com.example.OrderManagment.dto.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderResponseDto createOrder(CreateOrderRequestDto createOrderRequestDto);
    OrderResponseDto addProductInOrder(Long id, List<CreateOrderItemRequestDto> items);
    void removeItemOrder(Long orderId, Long orderItemId);
    Optional<OrderResponseDto> getOrderById(Long id);
    List<OrderResponseDto> getAllOrders();
    List<OrderResponseDto> getOrdersFromUser(Long userId);
    OrderResponseDto updateOrderStatus(Long orderId, UpdateOrderStatusRequestDto updateOrderStatusRequestDto);
    void cancelOrderItem(Long orderId, Long orderItemId);
    Long getTotalCountOrders();
    List<OrderStatusCountDto> getCountOrderByStatus();
    public BigDecimal getSumOrderByStatus();





}