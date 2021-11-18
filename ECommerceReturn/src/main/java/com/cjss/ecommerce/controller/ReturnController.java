package com.cjss.ecommerce.controller;

import com.cjss.ecommerce.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReturnController {
    @Autowired
    ReturnService cartService;

    @PostMapping("/return-product")
    public ResponseEntity returnProduct(Integer orderCode){
        return cartService.returnProduct(orderCode);
    }
}



