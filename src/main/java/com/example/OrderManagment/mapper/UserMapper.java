package com.example.OrderManagment.mapper;

import com.example.OrderManagment.Entity.User;
import com.example.OrderManagment.dto.CreateUserRequestDto;
import com.example.OrderManagment.dto.UserResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public User toEntity(CreateUserRequestDto dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());

        return user;
    }







    public UserResponseDto toDto(User user) {
        if (user == null) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
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
