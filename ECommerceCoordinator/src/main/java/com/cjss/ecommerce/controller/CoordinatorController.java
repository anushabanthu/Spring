package com.cjss.ecommerce.controller;

import com.cjss.ecommerce.entity.OrderEntity;
import com.cjss.ecommerce.model.Address;
import com.cjss.ecommerce.model.Login;
import com.cjss.ecommerce.model.Order;
import com.cjss.ecommerce.model.RegisterCustomer;
import com.cjss.ecommerce.service.CoordinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.Inet4Address;

@RestController
public class CoordinatorController {
    @Autowired
    CoordinatorService coordinatorService;

    @PostMapping("/register-customer")
    public ResponseEntity registerCustomer(@RequestBody RegisterCustomer customer){
        return coordinatorService.registerCustomer(customer);
    }
    @PostMapping("/login-user")
    public ResponseEntity loginUSer(@RequestBody Login login){
        return coordinatorService.loginUSer(login);
    }
    @PostMapping("/add-address")
    public ResponseEntity addAddress(@RequestBody Address address){
        return coordinatorService.addAddress(address);
    }
    @PostMapping("/add-products-to-cart")
    public ResponseEntity addProductsToCart(@RequestBody Order order){
        return coordinatorService.addProductsToCart(order);
    }
    @GetMapping("/view-cart")
    public ResponseEntity viewCart(){
        return coordinatorService.viewCart();
    }
    @PostMapping("/select-shipping-address")
    public ResponseEntity selectShippingAddress(String id,Integer orderCode){
        return coordinatorService.selectShippingAddress(id,orderCode);
    }
    @PostMapping("/select-billing-address")
    public ResponseEntity selectBillingAddress(String id,Integer orderCode){
        return coordinatorService.selectBillingAddress(id,orderCode);
    }
    @PostMapping("/place-order")
    public ResponseEntity placeOrder(Integer orderCode){
        return coordinatorService.placeOrder(orderCode);
    }
    @PostMapping("/get-order-status")
    public ResponseEntity getOrderStatus(Integer orderCode){
        return coordinatorService.getOrderStatus(orderCode);
    }
    @PostMapping("/return-product")
    public ResponseEntity returnProduct(Integer orderCode){
        return coordinatorService.returnProduct(orderCode);
    }
}



