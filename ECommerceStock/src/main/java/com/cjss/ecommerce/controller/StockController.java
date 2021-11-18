package com.cjss.ecommerce.controller;

import com.cjss.ecommerce.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {
    @Autowired
    StockService stockService;

    @PostMapping("/add-stock")
    public ResponseEntity addSKUStockDetailsById(Integer skuId, Integer stock){
        return stockService.addSKUStockDetailsById(skuId,stock);
    }
    @PostMapping("/get-stock-details-by-id")
    public ResponseEntity getSKUStockDetailsById(Integer id){
        return stockService.getSKUStockDetailsById(id);
    }
}



