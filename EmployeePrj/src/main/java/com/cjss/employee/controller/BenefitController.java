package com.cjss.employee.controller;

import com.cjss.employee.entity.BenefitEntity;
import com.cjss.employee.model.Benefit;
import com.cjss.employee.service.BenefitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BenefitController {
    @Autowired
    BenefitService benefitService;

    @PostMapping("/get-benefit-details-by-id")
    public Benefit getBenefitDetailsById(Integer id){
        System.out.println("BenefitController: getBenefits");
        return benefitService.getBenefitDetailsById(id);
    }
    @PostMapping("/add-benefit")
    public BenefitEntity addBenefitDetails(@RequestBody Benefit benefit){
        System.out.println("BenefitController: addBenefit");
        return benefitService.addBenefitDetails(benefit);
    }
    @DeleteMapping("/delete-benefit/{id}")
    public void deleteBenefit(@PathVariable int id){
        System.out.println("BenefitController: deleteBenefit");
        benefitService.deleteBenefitById(id);
    }
}



