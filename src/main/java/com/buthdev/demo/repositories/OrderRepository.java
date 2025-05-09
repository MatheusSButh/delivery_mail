package com.buthdev.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buthdev.demo.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}