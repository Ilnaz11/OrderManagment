package com.example.OrderManagment.Controller;

import com.example.OrderManagment.Service.OrderService;
import com.example.OrderManagment.dto.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponseDto createOrder(@RequestBody CreateOrderRequestDto createOrderRequestDto) {
        return orderService.createOrder(createOrderRequestDto);
    }

    @PostMapping("/{orderId}/items")
    public OrderResponseDto addProductInOrder(@PathVariable Long orderId,
                                              @RequestBody List<CreateOrderItemRequestDto> createOrderItemRequestDtoList) {
        return orderService.addProductInOrder(orderId, createOrderItemRequestDtoList);
    }

    @DeleteMapping("/{orderId}/orderItems/{orderItemId}/removeOrderItem")
    public void removeItemOrder(@PathVariable Long orderId, @PathVariable Long orderItemId) {
        orderService.removeItemOrder(orderId, orderItemId);
    }

    @GetMapping("/{id}")
    public Optional<OrderResponseDto> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/orders")
    public List<OrderResponseDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponseDto> getOrdersFromUser(@PathVariable Long userId) {
        return orderService.getOrdersFromUser(userId);
    }

    @PatchMapping("/update/{id}")
    public OrderResponseDto updateOrderStatus(@PathVariable Long id,
                                              @RequestBody UpdateOrderStatusRequestDto updateOrderStatusRequestDto) {
        return orderService.updateOrderStatus(id, updateOrderStatusRequestDto);
    }

    @PatchMapping("/{orderId}/orderItem/{orderItemId}/cancel")
    public void cancelOrderItem(@PathVariable Long orderId,
                                @PathVariable Long orderItemId) {
        orderService.cancelOrderItem(orderId, orderItemId);
    }

    @GetMapping("/analytics/count")
    public Long getTotalCountOrders() {
        return orderService.getTotalCountOrders();
    }

    @GetMapping("/analytics/status")
    public List<OrderStatusCountDto> getCountOrderByStatus() {
        return orderService.getCountOrderByStatus();
    }

    @GetMapping("/analytics/sum")
    public BigDecimal getSumOrderByStatus() {
        return orderService.getSumOrderByStatus();
    }

}