package com.cjss.student.controller;

import com.cjss.student.entity.StudentFeeEntity;
import com.cjss.student.entity.StudentMarksEntity;
import com.cjss.student.model.StudentMarks;
import com.cjss.student.service.StudentMarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentMarksController {
    @Autowired
    StudentMarksService studentMarksService;

    @PostMapping("/get-marks-details-by-id")
    public StudentMarks getMarksDetailsById(Integer id){
        return studentMarksService.getStudentMarksDetailsById(id);
    }

    @PostMapping("/add-marks-details")
    public StudentMarksEntity addMarksDetails(@RequestBody StudentMarks studentMarks){
        return studentMarksService.addMarksDetails(studentMarks);
    }

    @DeleteMapping("/delete-marks-details/{id}")
    public void deleteMarksDetails(@PathVariable int id){
        studentMarksService.deleteStudentMarksById(id);
    }
}



