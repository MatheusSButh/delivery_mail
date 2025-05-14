package com.buthdev.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buthdev.demo.exceptions.NotFoundException;
import com.buthdev.demo.model.Address;
import com.buthdev.demo.repositories.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private ViaCepService viaCepService;
	
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
	
	
	public Address findOrCreateAddress(String cep) {
		Address address = findAddressByCep(cep);
		
		if(address == null) {
			address = convertCepToAddress(cep);
			return createAddress(address);
		}
		else {
			return address;
		}
	}
	
	protected Address convertCepToAddress(String cep) {
		return viaCepService.convertAddress(cep);
	}
}
