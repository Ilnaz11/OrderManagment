package com.example.OrderManagment.Controller;

import com.example.OrderManagment.Service.OrderService;
import com.example.OrderManagment.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Orders", description = "Управление заказами")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponseDto createOrder(@RequestBody @Valid CreateOrderRequestDto createOrderRequestDto) {
        return orderService.createOrder(createOrderRequestDto);
    }

    @Operation(summary = "Добавление товара в заказ")
    @PostMapping("/{orderId}/items")
    public OrderResponseDto addProductInOrder(@PathVariable Long orderId,
                                              @RequestBody @Valid List<CreateOrderItemRequestDto> createOrderItemRequestDtoList) {
        return orderService.addProductInOrder(orderId, createOrderItemRequestDtoList);
    }

    @Operation(summary = "Удаление конкретной позиции заказа")
    @DeleteMapping("/{orderId}/orderItems/{orderItemId}/removeOrderItem")
    public void removeItemOrder(@PathVariable Long orderId, @PathVariable Long orderItemId) {
        orderService.removeItemOrder(orderId, orderItemId);
    }

    @GetMapping("/{id}")
    public OrderResponseDto getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(Long orderId) {
        orderService.deleteOrder(orderId);
    }

    @GetMapping
    public List<OrderResponseDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponseDto> getOrdersFromUser(@PathVariable Long userId) {
        return orderService.getOrdersFromUser(userId);
    }

    @PatchMapping("/update/{id}")
    public OrderResponseDto updateOrderStatus(@PathVariable Long id,
                                              @RequestBody @Valid UpdateOrderStatusRequestDto updateOrderStatusRequestDto) {
        return orderService.updateOrderStatus(id, updateOrderStatusRequestDto);
    }

    @Operation(summary = "Отмена отдельной позиции заказа")
    @PatchMapping("/{orderId}/orderItem/{orderItemId}/cancel")
    public void cancelOrderItem(@PathVariable Long orderId,
                                @PathVariable Long orderItemId) {
        orderService.cancelOrderItem(orderId, orderItemId);
    }

    @Operation(summary = "Общее кол-во заказов")
    @GetMapping("/analytics/count")
    public Long getTotalCountOrders() {
        return orderService.getTotalCountOrders();
    }

    @Operation(summary = "Кол-во заказов по каждому статусу")
    @GetMapping("/analytics/status")
    public List<OrderStatusCountDto> getCountOrderByStatus() {
        return orderService.getCountOrderByStatus();
    }

    @Operation(summary = "Общая сумма завершенных заказов")
    @GetMapping("/analytics/sum")
    public BigDecimal getSumOrderByStatus() {
        return orderService.getSumOrderByStatus();
    }

}