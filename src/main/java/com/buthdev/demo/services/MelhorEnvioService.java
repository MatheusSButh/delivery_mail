package com.buthdev.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.buthdev.demo.dtos.MelhorEnvioDto;
import com.google.gson.Gson;

@Service
public class MelhorEnvioService {
	    
	    @Value("${MELHOR_ENVIO_URL}")
	    private String melhorEnvioUrl;
	    
	    @Value("${MELHOR_ENVIO_TOKEN}")
	    private String token;
	    
	    @Value("${MELHOR_ENVIO_USER_AGENT}")
	    private String userAgent;
	   
	    public MelhorEnvioDto calcularFrete(String senderCep, String receiverCep) {
	        RestTemplate restTemplate = new RestTemplate();

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
	        headers.set("Authorization", "Bearer " + token);
	        headers.set("User-Agent", userAgent);

	        String body = String.format("""
	        {
	          "from": {
	            "postal_code": "%s"
	          },
	          "to": {
	            "postal_code": "%s"
	          },
	          "products": [
	            {
	              "id": "1",
	              "width": 12,
	              "height": 4,
	              "length": 17,
	              "weight": 0.3,
	              "insurance_value": 10,
	              "quantity": 1
	            }
	          ],
	          "services": "1",
	          "options": {
	            "receipt": false,
	            "own_hand": false,
	            "collect": false,
	            "reverse": false,
	            "non_commercial": true
	          }
	        }
	        """, senderCep, receiverCep);

	        HttpEntity<String> entity = new HttpEntity<>(body, headers);
	        
	        ResponseEntity<String> response = restTemplate.exchange(melhorEnvioUrl, HttpMethod.POST, entity, String.class);
	        String responseBody = response.getBody();
	        Gson gson = new Gson();
	        MelhorEnvioDto melhorEnvioDto = gson.fromJson(responseBody, MelhorEnvioDto.class);
	        
	        System.out.println("Response from Melhor Envio: " + response.getBody());

	        return melhorEnvioDto;
	 }
}