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
	    private static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5NTYiLCJqdGkiOiJhMzNhYzg2ZjM3MWM3MjZmMTMwNzYyMmFkYjMwMDUyYTU5ZjhmMzQ1NGRlNGYxZGQ3OTIwMjc1NzU1YmE3ZDdhOTcyMTIwZDUzZTQ0NTk1ZSIsImlhdCI6MTc0NzA4Nzk3MS4xODU3ODYsIm5iZiI6MTc0NzA4Nzk3MS4xODU3ODksImV4cCI6MTc3ODYyMzk3MS4xNjI2NjgsInN1YiI6IjllZGVhY2E4LTI4NDEtNGY0Yy05NmFlLWJhYjg4ZWJhMWViZCIsInNjb3BlcyI6WyJzaGlwcGluZy1jYWxjdWxhdGUiXX0.Hi1WzoKJ_PkdX_KrUc2vwR8HxSrDuQvVi9TId10Yu8coF5zkb1ObZigbytBl9YeTsd476pRBWLLg1_mK4CEwXOeLJd32ved1dqAMSqZb7vpAheeznBCsp3hMqpHrhki672ouhBWxlZ4810J4KejV-PmA9HctFYGY4x1rt2X8BufozoM6vivj0XUYlhIfiZgixlist_kWa1mLKQ0HgumMJzmDdEKXxCKJUczGGjyLcHvP-Stgf8zoAfd_joMxMvu0a2e4OBYR7hqmzGGtNIBp6qPrzZsL_iKWdA8226Wl46ZTYO1Jto0y3p2AZy6_skXvuFPYOtWTHMXQcNds2kuwWiopI4cBtG8jkVlwGemHqBQvfg_eETySxixw-OMhYXuyo-2C9-vsZDcIA3nE_ybZrK7BvL-y2YkSSTR_okzRmafhwm6Bc0oJhq2pSPctgn3aPI-F-79f6dvoDPXQIeVGDPFbw6SuJWpC2jYopmA1wEvRZv0krMXXWbS58qmeWjeSht_3QCpCXsdoboc1mgHFoNqcaDBqMG7836PNm5rXruZxNwz5yeiFQCXIB2mTrNzsGmcv98QtWf1LtwDXiCTBFObR2BDKFcKRh4Zs4dvw4boKNnA8yhONf6Nu3lYlPExQtoUDpT3FxJXh4oUkXph3UBPvIMz5uS_rE_ZlsbEKcAg";
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