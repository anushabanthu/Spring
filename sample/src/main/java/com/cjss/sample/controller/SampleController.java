package com.cjss.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cjss.sample.service.SampleService;

@RestController
public class SampleController{
	@Autowired
	SampleService s;
	
    @GetMapping("/")	// Bean method for this file (only 1 bean method is allowed)
    public String sample() {
		return s.sayHello();
    }
}