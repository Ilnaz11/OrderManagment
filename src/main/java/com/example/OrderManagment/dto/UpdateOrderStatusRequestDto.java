package com.example.OrderManagment.dto;

import com.example.OrderManagment.Entity.OrderItemStatus;
import com.example.OrderManagment.Entity.OrderStatus;
import lombok.Data;

@Data
public class UpdateOrderStatusRequestDto {
    private OrderStatus orderStatus;
    private OrderItemStatus orderItemStatus;
}
