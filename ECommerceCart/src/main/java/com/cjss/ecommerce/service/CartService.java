package com.cjss.ecommerce.service;

import com.cjss.ecommerce.entity.*;
import com.cjss.ecommerce.model.*;
import com.cjss.ecommerce.repository.QuantityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CartService {
	@Autowired
	private QuantityRepository quantityRepository;
	RestTemplate restTemplate = new RestTemplate();
	int shippingAddress = 0;
	int billingAddress = 0;
	int quantity = 0;

	public ResponseEntity addProductsToCart(ProductQuantity quantity){
		if(quantityRepository.existsById(quantity.getOrderCode()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order code already available. Please provide a different order code");
        String getSKUStockDetailsUrl = "http://localhost:8083/get-stock-details-by-id?id="+quantity.getSkuCode();
        HttpEntity<Integer> request = new HttpEntity<>(quantity.getSkuCode());
        ResponseEntity<Stock> response = restTemplate.exchange(getSKUStockDetailsUrl, HttpMethod.POST, request, Stock.class);
		if(response.getBody().getQuantityAvailable()-quantity.getQuantity()- this.quantity <0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stock is not available for selected product (CODE 400)\n");
//		TODO: check here whether the product and sku exists in inventory or not

		// If already exists in cart increasing count
		ProductQuantityEntity quantityEntity = new ProductQuantityEntity();
		if(quantityRepository.existsById(quantity.getSkuCode())) {
			quantity.setQuantity(quantity.getQuantity()+quantityRepository.getById(quantity.getSkuCode()).getQunatity());
		}
		quantityEntity.setOrderCode(quantity.getOrderCode());
		quantityEntity.setSkuCode(quantity.getSkuCode());
		quantityEntity.setQunatity(quantity.getQuantity());
		this.quantity = quantity.getQuantity()+this.quantity;
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(quantityRepository.save(quantityEntity));
	}

	public ResponseEntity viewCart() {
		List<String> prices = new ArrayList<>();
		List<Integer> subTotal = new ArrayList<>();
		Integer total=0;
		if (quantityRepository.count() > 0) {
			List<ProductQuantityEntity> productQuantityEntity = quantityRepository.findAll();    //findById is available by default from JPARepository
			productQuantityEntity.forEach(productQuantity -> {
				String getPriceDetailsUrl = "http://localhost:8082/get-price-details-by-id?id="+productQuantity.getSkuCode();
				String price = new String();
				HttpEntity<Integer> request = new HttpEntity<>(productQuantity.getSkuCode());
				ResponseEntity<Price> response = restTemplate.exchange(getPriceDetailsUrl, HttpMethod.POST, request, Price.class);
				price = "SKU code:" + response.getBody().getSkuCode() + " Price:" + response.getBody().getPrice()+" SubTotal:";
				prices.add(price);
//				ToDO: subtotal should come in the same line
				subTotal.add(response.getBody().getPrice());
			});
			for (int price : subTotal) {
				total = total + price;
			}
			prices.add(total.toString());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(prices);
		}
		else	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No items in cart (CODE 400)\n");
	}


	public ResponseEntity selectShippingAddress(String id){
		String getCustomerUrl = "http://localhost:8081/get-customer-details-by-id?id="+id;
		HttpEntity<String> request = new HttpEntity<>(id.split("_")[0]);
		ResponseEntity<RegisterCustomerEntity> response = restTemplate.exchange(getCustomerUrl, HttpMethod.POST, request, RegisterCustomerEntity.class);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response.getBody());
	}

	public ResponseEntity addNewBillingAddress(Address address){
		String addAddressUrl = "http://localhost:8081/add-address";
		HttpEntity<Address> request = new HttpEntity<>(address);
		System.out.println(address.getEmail());
		ResponseEntity<RegisterCustomerEntity> response = restTemplate.exchange(addAddressUrl, HttpMethod.POST, request, RegisterCustomerEntity.class);
		String addressCountUrl = "http://localhost:8081/get-address-count-by-id";
		HttpEntity<String> request1 = new HttpEntity<>(address.getEmail());
		ResponseEntity<Integer> response1 = restTemplate.exchange(addressCountUrl, HttpMethod.POST, request1, Integer.class);
		billingAddress = response1.getBody();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response.getBody());
	}
}
