package com.example.OrderManagment.mapper;

import com.example.OrderManagment.Entity.Order;
import com.example.OrderManagment.Entity.OrderItem;
import com.example.OrderManagment.dto.OrderItemResponse;
import com.example.OrderManagment.dto.OrderResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {


    private OrderItemResponse toItemDto(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }

        OrderItemResponse orderItemResponse = new OrderItemResponse();
        orderItemResponse.setId(orderItem.getId());
        orderItemResponse.setProductId(orderItem.getProduct().getId());
        orderItemResponse.setPriceAtOrderTime(orderItem.getPriceAtOrderTime());
        orderItemResponse.setQuantity(orderItem.getQuantity());
        orderItemResponse.setStatus(orderItem.getStatus());

        return orderItemResponse;
    }

    public OrderResponseDto toDto(Order order) {
        if (order == null) {
            return null;
        }

        OrderResponseDto orderResponseDto = new OrderResponseDto();

        orderResponseDto.setId(order.getId());
        orderResponseDto.setCurrentStatus(order.getCurrentStatus());
        orderResponseDto.setTotalPrice(order.getTotalPrice());
        orderResponseDto.setCreatedAt(order.getCreatedAt());
        orderResponseDto.setLastChange(order.getLastChange());

        List<OrderItemResponse> orderItemResponses = order.getOrderItems()
                .stream()
                .map(this::toItemDto)
                .toList();

        orderResponseDto.setItems(orderItemResponses);

        return orderResponseDto;
    }

    public List<OrderResponseDto> toDtoList(List<Order> order) {
        if (order == null) {
            return null;
        }

        return order.stream()
                .map(this::toDto)
                .toList();
    }

}
