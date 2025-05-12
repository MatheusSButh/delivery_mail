package com.buthdev.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buthdev.demo.exceptions.NotFoundException;
import com.buthdev.demo.model.Address;
import com.buthdev.demo.repositories.AddressRepository;

@Service
public class AddressService {

	
	@Autowired
	private AddressRepository addressRepository;
	
	public Address createAddress(Address address) {
		return addressRepository.save(address);
	}
	
	public Address findById(Long id) {
		return addressRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
	}
	
	public Address findAddressByCep(String cep) {
		return addressRepository.findAddressByCep(cep);
	}
}
