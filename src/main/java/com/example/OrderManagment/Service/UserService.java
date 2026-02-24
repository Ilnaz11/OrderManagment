package com.example.OrderManagment.Service;

import com.example.OrderManagment.Entity.User;
import com.example.OrderManagment.dto.CreateUserRequestDto;
import com.example.OrderManagment.dto.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserResponseDto createUser(CreateUserRequestDto userRequestDto);
    List<UserResponseDto> getAllUsers();
    void deleteUser(Long id);
    Optional<UserResponseDto> findById(Long id);

}
