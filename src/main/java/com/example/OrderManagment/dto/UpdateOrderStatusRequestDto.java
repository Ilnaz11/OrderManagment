package com.example.OrderManagment.dto;

import com.example.OrderManagment.Entity.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderStatusRequestDto {
    @NotNull(message = "Статус заказа не может быть пустым")
    private OrderStatus orderStatus;
//    @NotNull(message = "Статус позиции заказа не может быть пустым")
//    private OrderItemStatus orderItemStatus;
}
