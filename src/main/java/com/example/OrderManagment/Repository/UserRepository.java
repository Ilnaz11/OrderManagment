package com.example.OrderManagment.Repository;

import com.example.OrderManagment.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
