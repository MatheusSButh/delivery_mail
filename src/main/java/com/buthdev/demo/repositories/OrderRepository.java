package com.buthdev.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buthdev.demo.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}