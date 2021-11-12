package com.cjss.ecommerce.controller;

import com.cjss.ecommerce.model.Address;
import com.cjss.ecommerce.model.ProductQuantity;
import com.cjss.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/add-products-to-cart")
    public ResponseEntity addProductsToCart(@RequestBody ProductQuantity productQuantity){
        return cartService.addProductsToCart(productQuantity);
    }
    @GetMapping("/view-cart")
    public ResponseEntity viewCart(){
        return cartService.viewCart();
    }
    @PostMapping("/add-new-billing-address")
    public ResponseEntity addNewBillingAddress(@RequestBody Address address){
        return cartService.addNewBillingAddress(address);
    }
    @PostMapping("/select-shipping-address")
    public ResponseEntity selectShippingAddress(String id){
        return cartService.selectShippingAddress(id);
    }
}



