package com.cjss.ecommerce.service;

import com.cjss.ecommerce.entity.OrderEntity;
import com.cjss.ecommerce.entity.RegisterCustomerEntity;
import com.cjss.ecommerce.model.*;
import com.cjss.ecommerce.repository.AddressRepository;
import com.cjss.ecommerce.repository.RegisterCustomerRepository;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoordinatorService {
	RestTemplate restTemplate = new RestTemplate();

	public ResponseEntity registerCustomer(RegisterCustomer customer){
		String url = "http://localhost:8081/register-customer";
//		System.out.println("city:"+customer.getAddresses().get(0).getCity());
//		System.out.println("line1:"+customer.getAddresses().get(1).getLine1());
		HttpEntity<RegisterCustomer> request = new HttpEntity<>(customer);
		ResponseEntity<RegisterCustomerEntity> response = restTemplate.exchange(url, HttpMethod.POST, request, RegisterCustomerEntity.class);
		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}
	public ResponseEntity loginUSer(Login login){
		String url = "http://localhost:8081/login-user";
		HttpEntity<Login> request = new HttpEntity<>(login);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}
	public ResponseEntity addAddress(Address address){
		String url = "http://localhost:8081/add-address";
		HttpEntity<Address> request = new HttpEntity<>(address);
		ResponseEntity<RegisterCustomerEntity> response = restTemplate.exchange(url, HttpMethod.POST, request, RegisterCustomerEntity.class);
		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}
	public ResponseEntity addProductsToCart(Order order){
		String url = "http://localhost:8084/add-products-to-cart";
		HttpEntity<Order> request = new HttpEntity<>(order);
		ResponseEntity<OrderEntity> response = restTemplate.exchange(url, HttpMethod.POST, request, OrderEntity.class);
		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}
	public ResponseEntity viewCart(){
		String url = "http://localhost:8084/view-cart";
		HttpEntity request = new HttpEntity<>(null);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}
	public ResponseEntity selectShippingAddress(String id,Integer orderCode){
		String url = "http://localhost:8084/select-shipping-address?id="+id+"&orderCode="+orderCode;
		HttpEntity<String> request = new HttpEntity<>(id);
		ResponseEntity<OrderEntity> response = restTemplate.exchange(url, HttpMethod.POST, request, OrderEntity.class);
		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}
	public ResponseEntity selectBillingAddress(String id,Integer orderCode){
		String url = "http://localhost:8084/select-billing-address?id="+id+"&orderCode="+orderCode;
		HttpEntity<String> request = new HttpEntity<>(id);
		ResponseEntity<OrderEntity> response = restTemplate.exchange(url, HttpMethod.POST, request, OrderEntity.class);
		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}
	public ResponseEntity placeOrder(Integer orderCode){
		String url = "http://localhost:8084/place-order?orderCode="+orderCode;
		HttpEntity<Integer> request = new HttpEntity<>(orderCode);
		ResponseEntity<OrderEntity> response = restTemplate.exchange(url, HttpMethod.POST, request, OrderEntity.class);
		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}
	public ResponseEntity getOrderStatus(Integer orderCode){
		String url = "http://localhost:8084/get-order-status?orderCode="+orderCode;
		HttpEntity<Integer> request = new HttpEntity<>(orderCode);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}
	public ResponseEntity returnProduct(Integer orderCode){
		String url = "http://localhost:8086/return-product?orderCode="+orderCode;
		HttpEntity<Integer> request = new HttpEntity<>(orderCode);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}
}
