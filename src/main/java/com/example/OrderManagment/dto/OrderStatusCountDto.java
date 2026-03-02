package com.example.OrderManagment.dto;

import com.example.OrderManagment.Entity.OrderStatus;

public record OrderStatusCountDto(
        OrderStatus orderStatus,
        Long count
) {

}
