package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DemoRepo extends JpaRepository<User,Integer> {


    @Query("select u from User u where u.username = ?1")
    Optional<User> getUserbyUserName(String username);
    Optional<User> getUserById(int id);
}
