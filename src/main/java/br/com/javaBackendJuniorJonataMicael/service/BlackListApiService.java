package br.com.javaBackendJuniorJonataMicael.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.javaBackendJuniorJonataMicael.dto.BlackListResponseDTO;

@Service
public class BlackListApiService {
	
	Logger logger = LoggerFactory.getLogger(BlackListApiService.class);

	public static final String API_URL = "https://5e74cb4b9dff120016353b04.mockapi.io/api/v1/blacklist";
	public static final String BUSCA_PARAM = "?search=";
	
	public boolean verificarColaboradorPorCpf(String cpf){
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<BlackListResponseDTO>> response = restTemplate
				.exchange(API_URL + BUSCA_PARAM + cpf, HttpMethod.GET, null,
						new ParameterizedTypeReference<List<BlackListResponseDTO>>() {});
		
		return (response.getBody().isEmpty());
	}
}
