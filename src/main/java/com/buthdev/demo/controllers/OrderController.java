package com.buthdev.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buthdev.demo.dtos.OrderDTO;
import com.buthdev.demo.model.Order;
import com.buthdev.demo.services.OrderService;

@RestController
@RequestMapping(value = "orders")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		return ResponseEntity.ok().body(orderService.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDto) {
		orderService.createOrder(orderDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Order> updateOrder (@RequestBody OrderDTO orderDto, @PathVariable Long id) {
		orderService.updateOrder(orderDto, id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}