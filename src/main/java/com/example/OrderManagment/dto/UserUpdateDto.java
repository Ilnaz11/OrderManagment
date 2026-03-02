package com.example.OrderManagment.dto;

import lombok.Data;

@Data
public class UserUpdateDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
