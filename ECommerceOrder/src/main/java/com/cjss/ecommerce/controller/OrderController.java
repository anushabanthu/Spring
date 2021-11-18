package com.cjss.ecommerce.controller;

import com.cjss.ecommerce.model.Order;
import com.cjss.ecommerce.repository.OrderRepository;
import com.cjss.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    OrderService cartService;

    @PostMapping("/add-products-to-cart")
    public ResponseEntity addProductsToCart(@RequestBody Order order){
        return cartService.addProductsToCart(order);
    }
    @GetMapping("/view-cart")
    public ResponseEntity viewCart(){
        return cartService.viewCart();
    }

    @PostMapping("/select-shipping-address")
    public ResponseEntity selectShippingAddress(String id,Integer orderCode){
        return cartService.selectShippingAddress(id, orderCode);
    }
    @PostMapping("/select-billing-address")
    public ResponseEntity selectBillingAddress(String id,Integer orderCode){
        return cartService.selectBillingAddress(id, orderCode);
    }
    @PostMapping("/place-order")
    public ResponseEntity placeOrder(Integer orderCode){
        return cartService.placeOrder(orderCode);
    }
    @PostMapping("/get-order-status")
    public ResponseEntity getOrderStatus(Integer orderCode){
        return cartService.getOrderStatus(orderCode);
    }
    @PostMapping("/change-order-status")
    public ResponseEntity changeOrderStatus(Integer orderCode,String orderStatus){
        return cartService.changeOrderStatus(orderCode,orderStatus);
    }
    @PostMapping("/return-product")
    public ResponseEntity returnProduct(Integer orderCode){
        return cartService.returnProduct(orderCode);
    }
}



