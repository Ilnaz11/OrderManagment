package com.example.OrderManagment.service;

import com.example.OrderManagment.Entity.*;
import com.example.OrderManagment.Exception.BusinessException;
import com.example.OrderManagment.Exception.OrderNotFoundException;
import com.example.OrderManagment.Exception.ProductNotAvailableException;
import com.example.OrderManagment.Repository.OrderRepository;
import com.example.OrderManagment.Repository.ProductRepository;
import com.example.OrderManagment.Repository.UserRepository;
import com.example.OrderManagment.Service.OrderServiceImpl;
import com.example.OrderManagment.dto.CreateOrderItemRequestDto;
import com.example.OrderManagment.dto.CreateOrderRequestDto;
import com.example.OrderManagment.dto.UpdateOrderStatusRequestDto;
import com.example.OrderManagment.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImpTest {

    @Mock
    OrderRepository orderRepository;


    @Mock
    ProductRepository productRepository;

    @Mock
    OrderMapper orderMapper;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    OrderServiceImpl orderService;


    @Test
    void testGetOrderById_OrderNotFound_OrderNotFoundException() {
        when(orderRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(999L));

    }
    @Test
    void testCancelOrderItem_OrderCancelled() {
        
        Product product = new Product();
        product.setId(1L);
        product.setQuantity(5);

        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        orderItem.setProduct(product);
        orderItem.setQuantity(2);
        orderItem.setStatus(OrderItemStatus.ACTIVE);

        Order order = new Order();
        order.setId(1L);
        order.setCurrentStatus(OrderStatus.CREATED);
        order.setOrderItems(List.of(orderItem));
        order.setOrderHistories(new ArrayList<>());
        orderItem.setOrder(order);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order)); // Это надо чтобы не идти в бд т.e вернуть то что нам требуется

        orderService.cancelOrderItem(1L, 1L); // 2 этап вызов метода сервиса отмена позиции заказа

        assertEquals(OrderStatus.CANCELLED, order.getCurrentStatus());

        
    }
    
    @Test
    void testCanTransitionStatus_BusinessException() {
        UpdateOrderStatusRequestDto dto = new UpdateOrderStatusRequestDto();

        Order order = new Order();
        order.setId(1L);
        order.setCurrentStatus(OrderStatus.CREATED);
        order.setOrderHistories(new ArrayList<>());
        dto.setOrderStatus(OrderStatus.DELIVERED);
        
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        assertThrows(BusinessException.class, () -> orderService.updateOrderStatus(1L, dto));
    }

    @Test
    void testNotActiveProduct_ProductNotAvailable() {
        CreateOrderRequestDto dto1 = new CreateOrderRequestDto();

        User user = new User();
        user.setId(1L);
        dto1.setUserId(1L);

        CreateOrderItemRequestDto createOrderItemRequestDto = new CreateOrderItemRequestDto();
        createOrderItemRequestDto.setProductId(1L);
        createOrderItemRequestDto.setQuantity(1);

        dto1.setItems(List.of(createOrderItemRequestDto));

        Product product = new Product();
        product.setId(1L);
        product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        product.setQuantity(1);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        assertThrows(ProductNotAvailableException.class, () -> orderService.createOrder(dto1));
    }
}
