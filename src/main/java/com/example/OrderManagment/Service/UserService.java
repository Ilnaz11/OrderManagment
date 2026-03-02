package com.example.OrderManagment.Service;

import com.example.OrderManagment.Entity.User;
import com.example.OrderManagment.dto.CreateUserRequestDto;
import com.example.OrderManagment.dto.UserResponseDto;
import com.example.OrderManagment.dto.UserUpdateDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserResponseDto createUser(CreateUserRequestDto userRequestDto);
    UserResponseDto updateUser(Long id, UserUpdateDto userUpdateDto);
    List<UserResponseDto> getAllUsers();
    void deleteUser(Long id);
    Optional<UserResponseDto> findById(Long id);

}
