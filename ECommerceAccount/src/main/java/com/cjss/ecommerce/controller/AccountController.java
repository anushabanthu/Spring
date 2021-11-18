package com.cjss.ecommerce.controller;

import com.cjss.ecommerce.entity.RegisterCustomerEntity;
import com.cjss.ecommerce.model.Address;
import com.cjss.ecommerce.model.Login;
import com.cjss.ecommerce.model.RegisterCustomer;
import com.cjss.ecommerce.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/get-customer-details-by-id")
    public ResponseEntity getCustomerDetailsById(String id){
        return accountService.getCustomerDetailsById(id);
    }@PostMapping("/get-address-details-by-id")
    public ResponseEntity getAddressDetailsById(String id){
        return accountService.getAddressDetailsById(id);
    }
    @PostMapping("/register-customer")
    public ResponseEntity registerCustomer(@RequestBody RegisterCustomer customer){
        return accountService.registerCustomer(customer);
    }

    @PostMapping("/add-address")
    public ResponseEntity addAddressDetails(@RequestBody Address address){
        return accountService.addAddressDetails(address);
    }
    @PostMapping("/login-user")
    public ResponseEntity loginUser(@RequestBody Login login) throws Exception{
        return accountService.loginUser(login);
    }
    @PostMapping("/get-address-count-by-id")
    public Integer getAddressesCountById(@RequestBody String id) {
        return accountService.getAddressesCountById(id);
    }
}



