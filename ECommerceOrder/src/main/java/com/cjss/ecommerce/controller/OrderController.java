package com.cjss.ecommerce.controller;

import com.cjss.ecommerce.model.Order;
import com.cjss.ecommerce.repository.OrderRepository;
import com.cjss.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    OrderService cartService;

    @PostMapping("/add-products-to-cart/{email}")
    public ResponseEntity addProductsToCart(@PathVariable String email, @RequestBody Order order){
        return cartService.addProductsToCart(email,order);
    }
    @GetMapping("/view-cart/{email}")
    public ResponseEntity viewCart(@PathVariable String email){
        return cartService.viewCart(email);
    }

    @PostMapping("/select-shipping-address/{email}")
    public ResponseEntity selectShippingAddress(@PathVariable String email,String id,Integer orderCode){
        return cartService.selectShippingAddress(email,id, orderCode);
    }
    @PostMapping("/select-billing-address/{email}")
    public ResponseEntity selectBillingAddress(@PathVariable String email,String id,Integer orderCode){
        return cartService.selectBillingAddress(email,id, orderCode);
    }
    @PostMapping("/place-order/{email}")
    public ResponseEntity placeOrder(@PathVariable String email,Integer orderCode){
        return cartService.placeOrder(email,orderCode);
    }
    @PostMapping("/get-order-status/{email}")
    public ResponseEntity getOrderStatus(@PathVariable String email,Integer orderCode){
        return cartService.getOrderStatus(email,orderCode);
    }
    @PostMapping("/change-order-status/{email}")
    public ResponseEntity changeOrderStatus(@PathVariable String email,Integer orderCode,String orderStatus){
        return cartService.changeOrderStatus(email,orderCode,orderStatus);
    }
    @PostMapping("/return-product/{email}")
    public ResponseEntity returnProduct(@PathVariable String email,Integer orderCode){
        return cartService.returnProduct(email,orderCode);
    }
}



