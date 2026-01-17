package com.example.OrderManagment.dto;

public class CreateOrderItemRequestDto {
    private Long productId;
    private Long quantity;
}


//📦 Product (товары)
//Request DTO
//
//CreateProductRequestDto
//
//        UpdateProductRequestDto
//
//UpdateProductStatusRequestDto
//
//Response DTO
//
//ProductResponseDto
//
//ProductShortResponseDto (для OrderItem)
//
//🧾 Order (заказы)
//Request DTO
//
//CreateOrderRequestDto
//
//        UpdateOrderStatusRequestDto
//
//AddOrderItemRequestDto (если добавление отдельно)
//
//Response DTO
//
//OrderResponseDto
//
//        OrderShortResponseDto
//
//OrderItemResponseDto
//
//📜 OrderHistory (история)
//Response DTO
//
//OrderHistoryResponseDto
