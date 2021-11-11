package com.cjss.ecommerce.controller;

import com.cjss.ecommerce.entity.ProductEntity;
import com.cjss.ecommerce.model.*;
import com.cjss.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}



