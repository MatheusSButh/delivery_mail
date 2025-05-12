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
	    private static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5NTYiLCJqdGkiOiI1YWUzZjNkZjUwNzM4ZTEwYTYyNjVlZWVmZGI4ZDM0M2Y5OWU5Y2ZlZjViYzYwMWI5OTE1ODg5NzMwMWFjMTdmYzZjYTg3Y2MyNmQwZWJiNyIsImlhdCI6MTc0NzA5MDg5OS40NzU5OSwibmJmIjoxNzQ3MDkwODk5LjQ3NTk5MiwiZXhwIjoxNzc4NjI2ODk5LjQ2ODgzNiwic3ViIjoiOWVkZWFjYTgtMjg0MS00ZjRjLTk2YWUtYmFiODhlYmExZWJkIiwic2NvcGVzIjpbInNoaXBwaW5nLWNhbGN1bGF0ZSJdfQ.WTG4Y2Ofoml8AyNnHj67vu1QN_EQknqf84I8zmx9VE7rx_pK6Sd9CrE1Stx-Z7kABfALWdVy49Z4rR6TwCPgx5RXc7jnPWSitd0wU5p9ydZHSk2FjO8zeP1C8h-4fLsS98EsqDs-7RODCF1-wX2XbQgiOsRFBzeziwnkeZyMKhAdCDVome258x_gTAw522D8Po4Kv_TyNRC7mO3n6eqcpkbJUpudcpp9oFquPS8ys84rtIbQnFfOhle2Y-fn8eL1ZasGKKb1QZaO5dvjjD1avv5QQPnjQXR9zGhwzc2BZC5Hsafm7Yc5phhJmm5YsQ6eqQDur_E-f1QJN3TGwd31frhDjTNUdNVdPmaqGysZ2kkeTmAhfq2QRzWQLkpo97AO3zIHVrTVY5UwtEEin86yeK_hNlyU-7y3_GfHl1OS-QNJRStGuH0HUNvoIBPFds5bbItsK2xsfnBixCOS6qOC2_VtjNYYzLbVx8eLXkCyilqfvXdc3dpij1xnEhjTYiShbH1363u1UJIAcD1iNPfWP1cFDl90yZ65iZeKhj04uHSIcrXdLuHSWlPA0eFGgXHUAcp_fzkJ9dVILIlsx_2s6rxMb8ATCmys-AE9MHiWphCwMIO7Jv5Uot0eOPa0E5_SIh3c7BpuL5I2GGWX72Je_F2YbEYlPGawEMLuoJ4xouk";
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