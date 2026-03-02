package com.example.OrderManagment.mapper;

import com.example.OrderManagment.Entity.User;
import com.example.OrderManagment.dto.CreateUserRequestDto;
import com.example.OrderManagment.dto.UserResponseDto;
import com.example.OrderManagment.dto.UserUpdateDto;
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

    public void updateEntityFromDto(UserUpdateDto userUpdateDto, User user) {
        if (userUpdateDto == null || user == null) {
            return;
        }
        if (userUpdateDto.getUsername() != null) {
            user.setUsername(userUpdateDto.getUsername());
        }
        if (userUpdateDto.getEmail() != null) {
            user.setEmail(userUpdateDto.getEmail());
        }
        if (userUpdateDto.getFirstName() != null) {
            user.setFirstName(userUpdateDto.getFirstName());
        }
        if (userUpdateDto.getLastName() != null) {
            user.setLastName(userUpdateDto.getLastName());
        }
    }


    public UserResponseDto toDto(User user) {
        if (user == null) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setRole(user.getRole());
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
