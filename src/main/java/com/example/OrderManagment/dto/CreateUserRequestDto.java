package com.example.OrderManagment.dto;

import com.example.OrderManagment.Entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Map;


@Data
public class CreateUserRequestDto {
    @NotBlank(message = "Username не может быть пустым")
    private String username;
    @Email(message = "Email не может быть пустым")
    @Size(min = 3, max = 20, message = "Email не должен быть меньше 3 символов и более 20")
    private String email;
    private String firstName;
    private String lastName;
    @NotNull(message = "Роль не может быть пустым")
    private Role role;
}