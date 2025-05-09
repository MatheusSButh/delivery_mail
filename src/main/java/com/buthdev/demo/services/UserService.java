package com.buthdev.demo.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.buthdev.demo.dtos.UserDTO;
import com.buthdev.demo.exceptions.NotFoundException;
import com.buthdev.demo.model.User;
import com.buthdev.demo.repositories.UserRepository;

public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
	}
	
	public User createUser(UserDTO userDto) {
		User user = new User();
		convertToUser(userDto, user);
		
		return userRepository.save(user);
	}

	public User updateUser(UserDTO userDto, Long id) {
		User user = findById(id);
		convertToUser(userDto, user);
		return userRepository.save(user);
	}
	
	public void deleteUser(Long id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	
	private User convertToUser(UserDTO userDto, User user) {
		BeanUtils.copyProperties(userDto, user);
		return user;
	}
}
