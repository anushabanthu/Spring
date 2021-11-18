package com.cjss.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReturnService {
	RestTemplate restTemplate = new RestTemplate();

	public ResponseEntity returnProduct(Integer orderCode){
		String changeStatusUrl = "http://localhost:8084/return-product?orderCode="+orderCode;
		HttpEntity<Integer> request = new HttpEntity<>(orderCode);
		ResponseEntity<String> response = restTemplate.exchange(changeStatusUrl, HttpMethod.POST, request, String.class);

		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}
}
