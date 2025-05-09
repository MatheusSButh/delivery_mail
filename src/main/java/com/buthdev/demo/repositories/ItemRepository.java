package com.buthdev.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buthdev.demo.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}