package com.example.OrderManagment.Controller;

import com.example.OrderManagment.Entity.User;
import com.example.OrderManagment.Service.UserService;
import com.example.OrderManagment.Service.UserServiceImpl;
import com.example.OrderManagment.dto.CreateUserRequestDto;
import com.example.OrderManagment.dto.UserResponseDto;
import com.example.OrderManagment.dto.UserUpdateDto;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDto createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        return userService.createUser(createUserRequestDto);
    }

    @PutMapping("/update/{id}")
    public UserResponseDto updateUser(@PathVariable Long id,@RequestBody UserUpdateDto userUpdateDto) {
        return userService.updateUser(id, userUpdateDto);
    }

    @GetMapping("/users")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    Optional<UserResponseDto> findById(@PathVariable Long id) {
        return userService.findById(id);
    }
}