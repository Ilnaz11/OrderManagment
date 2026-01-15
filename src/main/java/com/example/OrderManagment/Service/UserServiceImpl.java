package com.example.OrderManagment.Service;

import com.example.OrderManagment.Entity.User;
import com.example.OrderManagment.Repository.UserRepository;
import com.example.OrderManagment.dto.UserResponseDto;
import com.example.OrderManagment.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public UserResponseDto createUser(User user) {
        User user1 = userRepository.save(user);
        return userMapper.toDto(user1);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserResponseDto> findById(Long id) {
        Optional<User> users = userRepository.findById(id);
        User user = users.
                orElseThrow(() -> new RuntimeException("Not found User with ID: " + id));
        return users.map(userMapper::toDto);
    }
}
