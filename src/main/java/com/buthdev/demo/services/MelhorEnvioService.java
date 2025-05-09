package com.buthdev.demo.services;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MelhorEnvioService {


	    private static final String MELHOR_ENVIO_URL = "https://sandbox.melhorenvio.com.br/api/v2/me/shipment/calculate";
	    private static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5NTYiLCJqdGkiOiI2ZjY2MjZhYzc3YzFhMTM0YjNlMmE3N2RiZTQxNmI4ZmUwYzc1NDJiZjI3ZmNhOTYzOTdlZjM0OGZjOTJhNDM1Y2I0NDFjOTc4NzUxZGQ1NSIsImlhdCI6MTc0Njc5ODI1NS4wMTY4NjIsIm5iZiI6MTc0Njc5ODI1NS4wMTY4NjUsImV4cCI6MTc3ODMzNDI1NS4wMDU5NzcsInN1YiI6IjllZGVhY2E4LTI4NDEtNGY0Yy05NmFlLWJhYjg4ZWJhMWViZCIsInNjb3BlcyI6WyJzaGlwcGluZy1jYWxjdWxhdGUiXX0.FUCUiYtce6XRcz_asIE7Wguw_Ub0ERzmCP5Q-7ln4fboHRe6g_VeUpB3Y81CZW4PmkWQ5xIYnZ-x9h_jbkM1Lso_yFj1xT7gTmclDyzNw5PUwKEDwcxx0nNwy3ROAB6wK_LRGAJHrhL1CgBePUe-sf5FXTTc4qYk-2mz-5abD8GEWjn9j5LlcVB9vIgdY28Tspap4nc9JwwDziGX567U-wr9xqa5PvOsdvFHxPJ5Kj0zqTzprclEb83t3jZWt04No8Xs3uWRG1OFl856PrPHhfHH4zp0yfBWHlLyRfhzlm8yqInniJocDyEJ6oUELl5mEssN9sPJUcFggvPPXO8YWLyQ3J3dPrmxn-IE0VmhQQ4RahIanapTjwB2dNl-YnSAO6ksyzGM_Irchb-aGaJP9KnVUxshtZX9wS6-FOLsOnaSJ0vCipCTfheLjBbG74YmNR50kwO2uTwMO4ZIF2XxeaKySeFWbdiuWM9Qerw7DmKGftKva9ETbce578EgYGuk7vN0eB7iu8f2xogYraKasqzwh8Lg9WMaqmRbBeK-D96iLEaiXa0d8eUSrG_r9m521uAyGs9fLGmh4KFUdwZwTgzp9fgGm3A8R0PdjpcFEQqUxH7iLdPVHfc6RwC3qXBnPBxa_t_ouWQwQ7sxrB7wxxGPNslqfIi8lq4-upl8o";
	    private static final String USER_AGENT = "Aplicação (matheusdsouzabuth@gmail.com)";

	    public String calcularFrete(String senderCep, String userCep) {
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
	          "services": [
	            "1"
	          ],
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

	        return response.getBody();
	 }
}