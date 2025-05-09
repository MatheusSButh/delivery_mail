package com.buthdev.demo.services;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buthdev.demo.dtos.OrderDTO;
import com.buthdev.demo.exceptions.NotFoundException;
import com.buthdev.demo.model.Order;
import com.buthdev.demo.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemService itemService;
	
	public List<Order> findAll(){
		return orderRepository.findAll();
	}
	
	public Order findById(Long id) {
		return orderRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
	}
	
	public Order createOrder(OrderDTO orderDto) {
		Order order = new Order();
		convertToOrder(orderDto, order);
		
		return orderRepository.save(order);
	}

	public Order updateOrder(OrderDTO orderDto, Long id) {
		Order order = findById(id);
		convertToOrder(orderDto, order);
		return orderRepository.save(order);
	}
	
	public void deleteOrder(Long id) {
		findById(id);
		orderRepository.deleteById(id);
	}
	
	
	private Order convertToOrder(OrderDTO orderDto, Order order) {
		order.setEstimatedDelivery(OffsetDateTime.now());
		order.setUser(userService.findById(orderDto.id()));
		order.setItems(itemService.findAllById(orderDto.items()));
		order.setSenderCep(orderDto.senderCep());
		return order;
	}
}