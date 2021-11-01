package com.cjss.student.controller;

import com.cjss.student.entity.StudentFeeEntity;
import com.cjss.student.model.StudentFee;
import com.cjss.student.service.StudentFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentFeeController {
    @Autowired
    StudentFeeService studentFeeService;

    @PostMapping("/get-fee-details-by-id")
    public StudentFee getFeeDetailsById(Integer id){
        return studentFeeService.getStudentFeeDetailsById(id);
    }

    @PostMapping("/add-fee-details")
    public StudentFeeEntity addFeeDetails(@RequestBody StudentFee studentFee){
        return studentFeeService.addFeeDetails(studentFee);
    }

    @DeleteMapping("/delete-fee-details/{id}")
    public void deleteFeeDetails(@PathVariable int id){
        studentFeeService.deleteStudentFeeById(id);
    }
}



