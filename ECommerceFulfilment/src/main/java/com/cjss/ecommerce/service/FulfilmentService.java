package com.cjss.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FulfilmentService {
	RestTemplate restTemplate = new RestTemplate();

	public ResponseEntity changeOrderStatus(Integer orderCode,String orderStatus){
		String changeStatusUrl = "http://localhost:8084/change-order-status?orderCode="+orderCode+"&orderStatus="+orderStatus;
		HttpEntity<Integer> request = new HttpEntity<>(orderCode);
		ResponseEntity<String> response = restTemplate.exchange(changeStatusUrl, HttpMethod.POST, request, String.class);
		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}
}
