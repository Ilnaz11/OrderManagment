package com.example.OrderManagment.dto;

import com.example.OrderManagment.Entity.Role;
import lombok.Data;


@Data
public class CreateUserRequestDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
}