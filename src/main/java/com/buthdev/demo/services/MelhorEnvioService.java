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
	    private static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5NTYiLCJqdGkiOiJiZWIxODFlY2FkMjAyNjQxNTkxZjkwMjhhZWNkZWFlYzkwNDVlMmI0Mjg0YzBhNWFkZDNiMmViZGIxNjMyMzIzNGEzZGY0MDU2ZDkzYTMyMCIsImlhdCI6MTc0NzA5MDYwOS4yMDk1NTksIm5iZiI6MTc0NzA5MDYwOS4yMDk1NjEsImV4cCI6MTc3ODYyNjYwOS4yMDIwMSwic3ViIjoiOWVkZWFjYTgtMjg0MS00ZjRjLTk2YWUtYmFiODhlYmExZWJkIiwic2NvcGVzIjpbInNoaXBwaW5nLWNhbGN1bGF0ZSJdfQ.uIfACivTanrso8UvI2ogKPTAwolPEf-VpyMz2gmmrUclkF8vE4w8Hn3LaUCQbdsKBrEE00uCsR8lYWD9Etaudmf9c4ODeUoNu72xYokyK2bOq_8s1FJiuD6NkERpUxmPEs-lqkiY8YhM4agFNxwKocozycE6IDqFyMOCvcAbQ6CrKcV-L5ekOvgLqVxfHVXtusygLRALdqSrEQcAro0pT0ZDx9cAf1JwMtSoXvxSl1i42HV9SumR_wo7iHC7NGv0YhiNMEBWPo2luTZ_aQdA_izHd3xSS6tFqGEvnkmuZeV0mptQQiZEEKspUeYjiaqnrLt8sxJ5kpVXtk6toEjpi_-0UbCkCO8eMjVoabRWPJ1GryzGPIFxAzZQI3nF2_tXN6WguQFZZ3MyS4xeXgYIdkqTeuCTkYfhD-6jjWG75wbfYxUhzPbTpK7MIiqj_BDOPmPeUrqw-CB_ucuoM_di6ZnBP1usAHCj-nsJCgmoO3mf1asU87X2jAVWizlpx0CUnLfzavPXF3Fh7hnARxMiQFDlmfbfHOF7Dk3Uhn-RsvKnAcuO14D9E3FsLhBpf2CA3jALcdGupmE0GD0UBe1elksFN7acxfkyhCZoQw4ey3XgpYPU4IlbYddE4EkBLMalcxTQnR5jOBKW0GdVBuiKio5BRxRCvHgJBdca-s_8CE0";
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