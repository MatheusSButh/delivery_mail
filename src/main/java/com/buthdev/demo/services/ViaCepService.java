package com.buthdev.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.buthdev.demo.model.Address;

@Service
public class ViaCepService {
	
	public Address convertAddress(String cep) {
		String url = "https://viacep.com.br/ws/" + cep + "/json/";
		
		RestTemplate restTemplate = new RestTemplate();
		Address address = restTemplate.getForObject(url, Address.class);
		
		return address;
	}
}