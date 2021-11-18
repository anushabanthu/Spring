package com.cjss.ecommerce.service;

import com.cjss.ecommerce.entity.*;
import com.cjss.ecommerce.model.*;
import com.cjss.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	RestTemplate restTemplate = new RestTemplate();

	public ResponseEntity addProductsToCart(Order order){
		List<String> qunatities = new ArrayList<>();
		OrderEntity orderEntity = new OrderEntity();
//		Checking whether the sku exists in inventory or not
		order.getQuantities().forEach(item->{
			String getSKUStockDetailsUrl = "http://localhost:8083/get-stock-details-by-id?id="+item.getSkuCode();
			HttpEntity<Integer> request = new HttpEntity<>(item.getSkuCode());
			ResponseEntity<Stock> response = restTemplate.exchange(getSKUStockDetailsUrl, HttpMethod.POST, request, Stock.class);
			qunatities.add(item.getSkuCode()+"_"+item.getQuantity()+"_"+response.getBody().getQuantityAvailable().toString());
		});
//		use map to get only sku codes
		List<String> skusWithoutStock = qunatities.stream().filter(quantity->Integer.valueOf(quantity.split("_")[1])>Integer.valueOf(quantity.split("_")[2])).collect(Collectors.toList()).stream().map(quantity->quantity.split("_")[0]).collect(Collectors.toList());
		if(!skusWithoutStock.isEmpty())	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stock is not available for items with sku codes:" + skusWithoutStock + " (CODE 400)\n");

		boolean orderExistsInCart = false;
		orderEntity.setOrderCode(order.getOrderCode());
		if (orderRepository.existsById(order.getOrderCode()) && orderRepository.getById(order.getOrderCode()).getStatus().equals("CART"))
			orderExistsInCart = true;
		if(orderExistsInCart)	{
			OrderEntity orderEntity1 = orderRepository.getById(order.getOrderCode());
			List<ProductQuantityEntity> productQuantityEntities = orderEntity1.getProductQuantityList();

			order.getQuantities().forEach(sku -> {
				ProductQuantityEntity productQuantityEntity = new ProductQuantityEntity();
//				TODO: if new order for same order code and sku codes, then add to the same sku. remove is not working from list
				productQuantityEntity.setOrderEntity(orderEntity);
				productQuantityEntity.setSkuCode(sku.getSkuCode().toString()+"_"+order.getOrderCode().toString());
				productQuantityEntity.setQuantity(sku.getQuantity());
				productQuantityEntities.add(productQuantityEntity);
			});
			orderEntity1.setProductQuantityList(productQuantityEntities);
			orderRepository.save(orderEntity1);
		}
		else {
			List<ProductQuantityEntity> quantityList = new ArrayList<>();
			order.getQuantities().forEach(sku -> {
				ProductQuantityEntity productQuantityEntity = new ProductQuantityEntity();
				productQuantityEntity.setOrderEntity(orderEntity);
				productQuantityEntity.setSkuCode(sku.getSkuCode().toString()+"_"+order.getOrderCode().toString());
				productQuantityEntity.setQuantity(sku.getQuantity());
				quantityList.add(productQuantityEntity);
			});
			orderEntity.setProductQuantityList(quantityList);
			orderEntity.setStatus("CART");
		}
		return ResponseEntity.status(HttpStatus.OK).body(orderRepository.save(orderEntity));
	}

	public ResponseEntity viewCart() {
		List<String> prices = new ArrayList<>();
		List<Integer> subTotal = new ArrayList<>();
		Integer total=0;
		if (orderRepository.count() > 0) {
			List<OrderEntity> orderEntities = orderRepository.findAll();
			orderEntities.stream().filter(order->order.getStatus().equals("CART")).findFirst().get().getProductQuantityList().forEach(sku -> {
				String getPriceDetailsUrl = "http://localhost:8082/get-price-details-by-id?id="+sku.getSkuCode().split("_")[0];
				String price = new String();
				HttpEntity<String> request = new HttpEntity<>(sku.getSkuCode());
				ResponseEntity<Price> response = restTemplate.exchange(getPriceDetailsUrl, HttpMethod.POST, request, Price.class);
				price = "SKU code:" + response.getBody().getSkuCode() + " Price:" + response.getBody().getPrice();
				prices.add(price);
//				ToDO: subtotal should come in the same line
				subTotal.add(response.getBody().getPrice());
			});
			for (int price : subTotal) {
				total = total + price;
			}
			prices.add(" SubTotal:"+total.toString());
			return ResponseEntity.status(HttpStatus.OK).body(prices);
		}
		else	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No items in cart (CODE 400)\n");
	}

	public ResponseEntity selectShippingAddress(String id,Integer orderCode){
		if(!orderRepository.existsById(orderCode))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order is not available with order code:"+orderCode+" CODE(400)");
		String getAddressUrl = "http://localhost:8081/get-address-details-by-id?id="+id;
		HttpEntity<String> request = new HttpEntity<>(id);
		ResponseEntity<Address> response = restTemplate.exchange(getAddressUrl, HttpMethod.POST, request, Address.class);

		OrderEntity orderEntity = orderRepository.getById(orderCode);
		List<AddressEntity> addressEntityList =	orderEntity.getAddressEntityList();
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setLine1(response.getBody().getLine1());
		addressEntity.setLine2(response.getBody().getLine2());
		addressEntity.setShippingAddress(response.getBody().getShippingAddress());
		addressEntity.setBillingAddress(response.getBody().getBillingAddress());
		addressEntity.setCity(response.getBody().getCity());
		addressEntity.setState(response.getBody().getState());
		addressEntity.setId(response.getBody().getId()+"#shippingAddress");
		addressEntity.setPostalCode(response.getBody().getPostalCode());
		addressEntity.setOrderEntity(orderEntity);
		addressEntityList.add(addressEntity);
		orderEntity.setAddressEntityList(addressEntityList);

		return ResponseEntity.status(HttpStatus.OK).body(orderRepository.save(orderEntity));
	}
	public ResponseEntity selectBillingAddress(String id,Integer orderCode){
		if(!orderRepository.existsById(orderCode))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order is not available with order code:"+orderCode+" CODE(400)");
		String getAddressUrl = "http://localhost:8081/get-address-details-by-id?id="+id;
		HttpEntity<String> request = new HttpEntity<>(id);
		ResponseEntity<Address> response = restTemplate.exchange(getAddressUrl, HttpMethod.POST, request, Address.class);

		OrderEntity orderEntity = orderRepository.getById(orderCode);
		List<AddressEntity> addressEntityList = orderEntity.getAddressEntityList();
		if(null!=orderEntity.getAddressEntityList().stream().filter(address->address.getId().split("#")[0].equals(id) && address.getId().split("#")[1].equals("billingAddress")).findFirst().orElse(null))
			return ResponseEntity.status(HttpStatus.OK).body(orderRepository.save(orderEntity));
		if(null!=orderEntity.getAddressEntityList().stream().filter(address->!address.getId().split("#")[0].equals(id) && address.getId().split("#")[1].equals("billingAddress")).findFirst().orElse(null))
		{
//			TODO: Not working properly
			addressEntityList.removeIf(address -> address.getId().split("#")[1].equals("billingAddress"));
		}
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setLine1(response.getBody().getLine1());
		addressEntity.setLine2(response.getBody().getLine2());
		addressEntity.setShippingAddress(response.getBody().getShippingAddress());
		addressEntity.setBillingAddress(response.getBody().getBillingAddress());
		addressEntity.setCity(response.getBody().getCity());
		addressEntity.setState(response.getBody().getState());
		addressEntity.setId(response.getBody().getId() + "#billingAddress");
		addressEntity.setPostalCode(response.getBody().getPostalCode());
		addressEntity.setOrderEntity(orderEntity);
		addressEntityList.add(addressEntity);
		orderEntity.setAddressEntityList(addressEntityList);

		return ResponseEntity.status(HttpStatus.OK).body(orderRepository.save(orderEntity));
	}

	public ResponseEntity placeOrder(Integer orderCode){
		if(!orderRepository.existsById(orderCode))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order code:"+orderCode+" invalid CODE(400)");
		OrderEntity orderEntity = orderRepository.getById(orderCode);
		if(orderEntity.getStatus()!="CART")	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Given order with orderCode:"+orderCode+" is not available in cart CODE(400)");
		List<String> qunatities = new ArrayList<>();
		orderEntity.getProductQuantityList().forEach(item->{
			String getSKUStockDetailsUrl = "http://localhost:8083/get-stock-details-by-id?id="+item.getSkuCode().split("_")[0];
			HttpEntity<String> request = new HttpEntity<>(item.getSkuCode());
			ResponseEntity<Stock> response = restTemplate.exchange(getSKUStockDetailsUrl, HttpMethod.POST, request, Stock.class);
			qunatities.add(item.getSkuCode()+"_"+item.getQuantity()+"_"+response.getBody().getQuantityAvailable().toString());
		});
//		use map to get only sku codes
		List<String> skusWithoutStock = qunatities.stream().filter(quantity->Integer.valueOf(quantity.split("_")[2])>Integer.valueOf(quantity.split("_")[3])).collect(Collectors.toList()).stream().map(quantity->quantity.split("_")[0]).collect(Collectors.toList());
		if(!skusWithoutStock.isEmpty())	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stock is not available for items with sku codes:" + skusWithoutStock + " (CODE 400)\n");


		List<String> quantities = new ArrayList<>();
//		checking if billing and shipping addresses are provided
		String id;
		if (orderEntity.getAddressEntityList().size() == 0)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Billing and shipping addresss are not provided for orderCode:" + orderCode + " (CODE 400)\n");
		else if(orderEntity.getAddressEntityList().size()==1) {
			id = orderEntity.getAddressEntityList().get(0).getId();
			if (!id.contains("shippingAddress"))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Shipping address is not provided for orderCode:" + orderCode + " (CODE 400)\n");
			else if (!id.contains("billingAddress"))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Billing address is not provided for orderCode:" + orderCode + " (CODE 400)\n");
		}
		//		Placing order
		orderEntity.getProductQuantityList().forEach(item->{
			Integer stock=-item.getQuantity();
			String addStockUrl = "http://localhost:8083/add-stock?skuId="+item.getSkuCode().split("_")[0]+"&stock="+stock;
			HttpEntity<String> request = new HttpEntity<>(item.getSkuCode());
			restTemplate.exchange(addStockUrl, HttpMethod.POST, request, Stock.class);
		});
		orderEntity.setStatus("RECEIVED");
		orderEntity = orderRepository.save(orderEntity);
		return ResponseEntity.status(HttpStatus.OK).body(orderEntity);
	}
	public ResponseEntity getOrderStatus(Integer orderCode){
		if(!orderRepository.existsById(orderCode))	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order is not available with orderCode:"+orderCode+" (CODE 400)\n");
		return ResponseEntity.status(HttpStatus.OK).body("Order state:"+orderRepository.getById(orderCode).getStatus());
	}

	public ResponseEntity changeOrderStatus(Integer orderCode, String orderStatus) {
		//RECEIVED to PROCESSING, PACKING, SHIPPING, DELIVERED
		OrderEntity orderEntity = orderRepository.getById(orderCode);
		String currentStatus = orderEntity.getStatus();
		boolean changeStatus = true;
		if ((currentStatus.equals("RECEIVED") && orderStatus.equals("PROCESSING")) || (currentStatus.equals("PROCESSING") && orderStatus.equals("PACKING"))
				|| (currentStatus.equals("PACKING") && orderStatus.equals("SHIPPING")) || (currentStatus.equals("SHIPPING") && orderStatus.equals("DELIVERED")))
		{
			orderEntity.setStatus(orderStatus);
			orderRepository.save(orderEntity);
			return ResponseEntity.status(HttpStatus.OK).body("Status change from "+currentStatus+" to "+orderStatus+" Succssful");
		}
		else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid change order status request - Current:"+currentStatus+",Required:"+orderStatus);
	}

	public ResponseEntity returnProduct(Integer orderCode){
		OrderEntity orderEntity = orderRepository.getById(orderCode);
		String currentStatus = orderEntity.getStatus();
		if (currentStatus.equals("RECEIVED") || currentStatus.equals("PROCESSING") || currentStatus.equals("PACKING"))
		{
			orderEntity.getProductQuantityList().forEach(item -> {
				String addStockUrl = "http://localhost:8083/add-stock?skuId=" + item.getSkuCode().split("_")[0] + "&stock=" + item.getQuantity();
				HttpEntity<String> request = new HttpEntity<>(item.getSkuCode());
				restTemplate.exchange(addStockUrl, HttpMethod.POST, request, Stock.class);
			});
			orderEntity.setStatus("RETURNED");
			orderRepository.save(orderEntity);
			return ResponseEntity.status(HttpStatus.OK).body("Order with orderCode:"+orderCode+" is returned");
		}
		else	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order cant be returned from this state:"+currentStatus);
	}
}
