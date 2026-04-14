package com.example.OrderManagment.service;

import com.example.OrderManagment.Exception.OrderNotFoundException;
import com.example.OrderManagment.Repository.OrderRepository;
import com.example.OrderManagment.Repository.ProductRepository;
import com.example.OrderManagment.Repository.UserRepository;
import com.example.OrderManagment.Service.OrderServiceImpl;
import com.example.OrderManagment.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void getOrderById_orderNotFound_OrderNotFoundException() {
        when(orderRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(999L));

    }
    @Test
    void cancelOrderItem_allCancelled_orderBecomesCANCELLED() {
        // arrange
        Order order = new Order();
        order.setCurrentStatus(OrderStatus.CREATED);
        OrderItem item = new OrderItem();
        item.setId(1L);
        item.setStatus(OrderItemStatus.ACTIVE);
        item.setQuantity(2);
        item.setProduct(new Product());
        item.getProduct().setQuantity(5);
        order.setOrderItems(List.of(item));
        order.setOrderHistories(new ArrayList<>());

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        // act
        orderService.cancelOrderItem(1L, 1L);

        // assert
        assertEquals(OrderStatus.CANCELLED, order.getCurrentStatus());
    }
}
