package com.cjss.ecommerce.controller;

import com.cjss.ecommerce.service.FulfilmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FulfilmentController {
    @Autowired
    FulfilmentService fulfilmentService;

    @PostMapping("/change-order-status")
    public ResponseEntity changeOrderStatus(Integer orderCode,String orderStatus){
        return fulfilmentService.changeOrderStatus(orderCode, orderStatus);
    }
}



