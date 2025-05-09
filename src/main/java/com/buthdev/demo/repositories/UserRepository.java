package com.buthdev.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buthdev.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}