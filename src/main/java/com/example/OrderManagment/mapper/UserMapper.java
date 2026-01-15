package com.example.OrderManagment.mapper;

import com.example.OrderManagment.Entity.User;
import com.example.OrderManagment.dto.UserResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public UserResponseDto toDto(User user) {
        if (user == null) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setEmail(user.getEmail());

        return userResponseDto;
    }

    public List<UserResponseDto> toDtoList(List<User> users) {
        return users.
                stream()
                .map(this::toDto)
                .toList();
    }
}
