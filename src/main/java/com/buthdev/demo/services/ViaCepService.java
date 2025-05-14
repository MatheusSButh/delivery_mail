package com.buthdev.demo.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.buthdev.demo.model.Address;

@Service
public class ViaCepService {
	
	@Value("${VIA_CEP_URL}")
	private String viaCepUrl;
	
	public Address convertAddress(String cep) {
	
		String url = viaCepUrl + cep + "/json/";
		
		RestTemplate restTemplate = new RestTemplate();
		Address address = restTemplate.getForObject(url, Address.class);
		
		return address;
	}
}