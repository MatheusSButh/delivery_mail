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
	    private static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5NTYiLCJqdGkiOiI5MDc0MmI4YjU5MjFjMjg1YTFmNGY4ODEyZGUyNjJlMTg0ZmU4YTQ0MzA4NWM1MTI2YWFkNjdkNmY5MzRkYmY4YjkzYWIwNmYwNjQ4YmY3NyIsImlhdCI6MTc0NjgxODI1Ni4xNzQwMTEsIm5iZiI6MTc0NjgxODI1Ni4xNzQwMTQsImV4cCI6MTc3ODM1NDI1Ni4xNjI4NSwic3ViIjoiOWVkZWFjYTgtMjg0MS00ZjRjLTk2YWUtYmFiODhlYmExZWJkIiwic2NvcGVzIjpbInNoaXBwaW5nLWNhbGN1bGF0ZSJdfQ.BCZ3rEROlo33My62tSkLutNdu8a996-UzN5K-7izyWy0-TynL5nzRhijFxALFIMEgwcq-8U9QfDkBTYXBtz4dIpLuA3o9Dlq8aVmWyu3IW_DwfzFeNwLVqN92tkkQxFT5H-Dy_5_oc0OHnOmRgs5bClYr4DIPzsJOJf4K7TdKLql_Dl0bwEBH1ltA4Xc8aSq3sWm63_VJiNUbRsvL05aBeBwj3ivulBVMJdSmWxWcMPm9x3krguHtzeJjH1UMtBV398FLpqQzZ7DGAn8bpc6vQieE4QYZ8mazzXhGc1ZDtgm9_7gqJ6LnoIt035ZdsI9JckyNHo-Xi0--AGHukQiKMa1R1jSSwFZl4J1ke8KVjfDy85V5v_X7UzgJ1QKEgI0PtBaK4l2C4PltqEd1JOcn0Fi92grVHbTZxVXSR729gqY_P1885hJDW3gy3dvvsl3asDJETkz5oDepUckFDBWspZfx4hq81qme99GsdTRVXbIb6f39cPBU3RZfn_L9czDnKGLgsr5_S7aYcsXASUCJx-zkcojYuba3EIhii1w4Tu4o3Diygne0M7IpKH427Ekk-cUBqzz_Ac4pr6x7c34NJUcApBHKMdsx5DuMBAT0Izhue-L9R85I--YG3vk0dVJzZJMvApbPOBl72DoYwl0lwRvxq1Y4Yurx-H_Pjq4Hnc ";
	    private static final String USER_AGENT = "Aplicação (matheusdsouzabuth@gmail.com)";

	    public MelhorEnvioDto calcularFrete(String senderCep, String userCep) {
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
	        """, senderCep, userCep);

	        HttpEntity<String> entity = new HttpEntity<>(body, headers);

	        ResponseEntity<String> response = restTemplate.exchange(MELHOR_ENVIO_URL, HttpMethod.POST, entity, String.class);

	        String responseBody = response.getBody();
	        Gson gson = new Gson();
	        MelhorEnvioDto melhorEnvioDto = gson.fromJson(responseBody, MelhorEnvioDto.class);
	        
	        System.out.println("Response from Melhor Envio: " + response.getBody());

	        
	        return melhorEnvioDto;
	 }
}