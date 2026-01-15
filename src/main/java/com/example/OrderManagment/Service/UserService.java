package com.example.OrderManagment.Service;

import com.example.OrderManagment.Entity.User;
import com.example.OrderManagment.dto.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserResponseDto createUser(User user);
    List<UserResponseDto> getAllUsers();
    void deleteUser(Long id);
    Optional<UserResponseDto> findById(Long id);

}
