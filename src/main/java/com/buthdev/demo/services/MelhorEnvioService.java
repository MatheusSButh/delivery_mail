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
	    private static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5NTYiLCJqdGkiOiI3ZTlhZDYxZjY0MmVjYWEzYjhiYTA2ZmZjY2VhZDE5NWEwNzFjYmU1NjU2MjgwODk1NzYzM2EzMWIxMTZmYjUwMjUyMDZiNTk0OTZlOWQ4YiIsImlhdCI6MTc0Njk5NDgzOS4xNTMzMzcsIm5iZiI6MTc0Njk5NDgzOS4xNTMzNCwiZXhwIjoxNzc4NTMwODM5LjE0NDAzMSwic3ViIjoiOWVkZWFjYTgtMjg0MS00ZjRjLTk2YWUtYmFiODhlYmExZWJkIiwic2NvcGVzIjpbInNoaXBwaW5nLWNhbGN1bGF0ZSJdfQ.L4Vq_J3fTtbm9RCpIf9xVWG83byr0HGbjXs59SjiGq0vkX1fHZuZkyvadZmRuCtdrwPeAKBw1X61KZ8HFEPrnhaZhMVQ2OuKYyAS0GiUVwoMlcYCrT03DCfafoM740Om4LqrR7Zvqdq2OWPxW9RG850n4ks6uZarEjKlcQ1tq5X9uO5yD3F8mfJ_LaKH04ALxWLOQhteS8CPKZakykCoiCnnAUl8FpqFOYbxI1rnZ4xwBGdBMZOkjI_eif7LI0KSAMkdecZ_iIV39OBC8HLzBwbDVJlPjteyhfeh_zIueZ66KNU6rgtOT_N4NOFM0YnZv_pTz10T7NLO-P_x4wZF1zXLQM6gm9k6_BggYXp_nu40_TzSMH7nmF3Jv9DT8EJoGn-jL1GBwrZWpCfVeL4F_CdfFU-3P2fBDi4hUThUcJZ9tDX5m9gbbODGxSQoYYsNZPVwL9an7LCZMtShZ93Klih3vOBO4LxAMT-uBkJWrWgtOjmSx8hXUtiXW_2HYT4MINkz-ddjrZ9w4_reL4iGomewqw2lpw83mLF__l6BXWpnrViIMojdyvEtNL8W-Ptlez09hOYH0hBLepOqGE-nH3avW-mljRT5eTDTItankZA--XKYDyIxQNZVlEDL_U88gytXjKl4W6-eoFMJ-rX9YBitKrDa_FoOVh-mliW_8GM";
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

	        return melhorEnvioDto;
	 }
}