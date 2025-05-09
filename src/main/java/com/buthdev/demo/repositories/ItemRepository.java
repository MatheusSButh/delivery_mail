package com.buthdev.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buthdev.demo.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	
	
}