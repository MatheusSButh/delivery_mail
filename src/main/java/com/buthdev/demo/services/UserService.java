package com.buthdev.demo.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.buthdev.demo.dtos.UserDTO;
import com.buthdev.demo.exceptions.InvalidCepException;
import com.buthdev.demo.exceptions.NotFoundException;
import com.buthdev.demo.model.Address;
import com.buthdev.demo.model.User;
import com.buthdev.demo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	AddressService addressService;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
	}
	
	public User createUser(UserDTO userDto) {
		User user = new User();
		convertToUser(userDto, user);
		
		Address address = user.getAddress();
		
		user.setAddress(addressService.findOrCreateAddress(address.getCep()));
	
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
		try {
		BeanUtils.copyProperties(userDto, user);
		user.setAddress(addressService.convertCepToAddress(userDto.cep()));
		return user;
		}
		catch(HttpClientErrorException e) {
			throw new InvalidCepException();
		}
  }
}
