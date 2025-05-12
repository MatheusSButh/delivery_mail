package com.buthdev.demo.services;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buthdev.demo.dtos.MelhorEnvioDto;
import com.buthdev.demo.dtos.OrderDTO;
import com.buthdev.demo.exceptions.InvalidDeliveryException;
import com.buthdev.demo.exceptions.NotFoundException;
import com.buthdev.demo.model.Item;
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
	
	@Autowired
	private MelhorEnvioService melhorEnvioService;
	
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
		
		try {
		order.setOrderDate(OffsetDateTime.now());
		order.setUserReceiver(userService.findById(orderDto.receiverId()));
		order.setUserSender(userService.findById(orderDto.senderId()));
		order.setItems(itemService.findAllById(orderDto.items()));
		
		MelhorEnvioDto melhorEnvioDto = calcularFrete(order.getUserSender().getCep(), order.getUserReceiver().getCep());
		
		order.setEstimatedDelivery(order.getOrderDate().plusDays(melhorEnvioDto.getDelivery_time()));
		
		double itemsTotalValue = 0;
		for(Item x : order.getItems()) {
			itemsTotalValue += x.getValue();
		}
		order.setTotalValue(itemsTotalValue + melhorEnvioDto.getPrice());
		
		return order;
		}
		catch(RuntimeException e) {
			throw new InvalidDeliveryException();
		}
	}
	
	private MelhorEnvioDto calcularFrete(String senderCep, String receiverCep) {
		return melhorEnvioService.calcularFrete(senderCep, receiverCep);
	}
}