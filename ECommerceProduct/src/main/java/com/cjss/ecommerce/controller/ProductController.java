package com.cjss.ecommerce.controller;

import com.cjss.ecommerce.entity.ProductEntity;
import com.cjss.ecommerce.entity.StockEntity;
import com.cjss.ecommerce.model.*;
import com.cjss.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity add(@RequestBody Product product){
        return productService.addProduct(product);
    }
    @PostMapping("/get-product-details-by-id")
    public ResponseEntity getProductDetailsById(Integer id){
        return productService.getProductDetailsById(id);
    }
    @PostMapping("/add-product-sku")
    public ResponseEntity addProductSku(@RequestBody ProductSku productSku){
        return productService.addProductSku(productSku);
    }
    @PostMapping("/get-product-sku-details-by-id")
    public ResponseEntity getProductSkuDetailsById(Integer id){
        return productService.getProductSkuDetailsById(id);
    }
    @PostMapping("/add-price")
    public ResponseEntity addPrice(@RequestBody Price price){
        return productService.addPrice(price);
    }
    @PostMapping("/get-price-details-by-id")
    public ResponseEntity getPriceDetailsById(Integer id){
        return productService.getPriceDetailsById(id);
    }

//    @PostMapping("/rest")
//    public void rest(Integer id) {
//        System.out.println("id:"+id);
//        RestTemplate restTemplate = new RestTemplate();
//        String addStockUrl = "http://localhost:8083/add-stock";
////        Price price = new Price();
////        price.setCurrency("inr");
////        price.setSkuCode(1);
////        price.setPrice(1);
//        HttpEntity<Integer> request = new HttpEntity<>(id);
//        System.out.println("request:"+request);
//        ResponseEntity<StockEntity> response = restTemplate.exchange(addStockUrl, HttpMethod.POST, request, StockEntity.class);
//        System.out.println(response.getBody());
//    }
}



