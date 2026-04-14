package com.example.OrderManagment.Service;

import com.example.OrderManagment.Entity.User;
import com.example.OrderManagment.Exception.UserNotFoundException;
import com.example.OrderManagment.Repository.UserRepository;
import com.example.OrderManagment.dto.CreateUserRequestDto;
import com.example.OrderManagment.dto.UserResponseDto;
import com.example.OrderManagment.dto.UserUpdateDto;
import com.example.OrderManagment.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDto createUser(CreateUserRequestDto dto) {
        log.info("User is created");
        User user = userMapper.toEntity(dto);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        log.info("Get all users");
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        log.info("Delete user by id: {}", id);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserUpdateDto userUpdateDto) {
        Optional<User> userOptional = userRepository.findById(id);

        User user = userOptional
                .orElseThrow(() -> new UserNotFoundException("Not found user with ID: " + id));

        userMapper.updateEntityFromDto(userUpdateDto, user);
        User savedUser = userRepository.save(user);

        log.info("Update user with id: {}", id);

        return userMapper.toDto(user);
    }

    @Override
    public Optional<UserResponseDto> findById(Long id) {
        log.info("Get user by id: {}", id);
        Optional<User> users = userRepository.findById(id);
        User user = users.
                orElseThrow(() -> new UserNotFoundException("Not found User with ID: " + id));
        return users.map(userMapper::toDto);
    }
}