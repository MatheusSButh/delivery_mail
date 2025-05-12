package com.buthdev.demo.services;

import java.util.List;

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


	    private static final String MELHOR_ENVIO_URL = "https://sandbox.melhorenvio.com.br/api/v2/me/shipment/calculate";
	    private static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5NTYiLCJqdGkiOiI3ZTE0ZDgzOTYxOGNlOTNiMGM1YTJhZGQ3YzE0YjE2ZThiNWZjNWU4YmJjMGRmMmI1Mzg3NGM2MmM4NjViOGYxMTlkZWE2MjMyNDUzMjQ5OCIsImlhdCI6MTc0NzA1ODg2My41MDg1NzIsIm5iZiI6MTc0NzA1ODg2My41MDg1NzUsImV4cCI6MTc3ODU5NDg2My41MDA0MzcsInN1YiI6IjllZGVhY2E4LTI4NDEtNGY0Yy05NmFlLWJhYjg4ZWJhMWViZCIsInNjb3BlcyI6WyJzaGlwcGluZy1jYWxjdWxhdGUiXX0.OWnKcO-iiEv9T7AJ7CFQGlP4_bXgogSMHtqEA3h3fYLeh0OIwybY2beAUxJ0lv7N9PmLJd4zlgclY-xG7o2bOaV_dOY_2cfDq5aBxZO4ybtlpON01GzwtBAMp6EM0MQ28uteN40c46nZY48UXgoYspUH1VFRU7Lk7UejEI23qQZn3xqwArs_MzsQOHne4gtVhulydYLPSiYI9cZVAOejOMGjPPzu4lR-CHSpA_1maJw3ukm6nY3xI2lBwneROzDSZefP21Vma9Ys27yhrPyTdSu4ee7TmK6Voa_SGpdK7mObgBC93nk0cJEeT2XAS53ccknOCP7Ynguit7ZIK9fT8I3nS4_d0eIIIAMliBzBQWl58fmVksyeQNdCEpDXVmgnqRM3PPNqVZqpebw3AAcE9qDah4T_QPMITLGwIQDYxQ_J6ZkB0dM-dumwRy_ft8iWw4LA1CgrhUpoHByriguRAGu-43utSQMq5XVsFAObyNCBHRCiIEFL4aUWtQSdYOGn_I9CgDfJpupu3F20j7TCz2EqNRoUo48jdQVvOfNRBjFuhznkrcufdqix1bAqfia3e9lmTbJa-9u77UlZ0jCETga7O1ubo5rGuV_BpmHDJDjyUX87kGzAwae5pyvE-2f0VFfKYl_bZxwgKIzFFqvyaFoAfIJwBM0KMKu82PQlfYM";
	    private static final String USER_AGENT = "Aplicação (matheusdsouzabuth@gmail.com)";

	    public MelhorEnvioDto calcularFrete(String senderCep, String receiverCep) {
	        RestTemplate restTemplate = new RestTemplate();

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
	        headers.set("Authorization", "Bearer " + TOKEN);
	        headers.set("User-Agent", USER_AGENT);

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
	        
	        ResponseEntity<String> response = restTemplate.exchange(MELHOR_ENVIO_URL, HttpMethod.POST, entity, String.class);
	        String responseBody = response.getBody();
	        Gson gson = new Gson();
	        MelhorEnvioDto melhorEnvioDto = gson.fromJson(responseBody, MelhorEnvioDto.class);
	        
	        System.out.println("Response from Melhor Envio: " + response.getBody());

	        return melhorEnvioDto;
	 }
}