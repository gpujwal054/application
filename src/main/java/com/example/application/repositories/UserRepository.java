package com.example.application.repositories;

import com.example.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findAll();
    User findByUsername(String username);
}
