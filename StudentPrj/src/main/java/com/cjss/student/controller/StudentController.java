package com.cjss.student.controller;
import com.cjss.student.entity.StudentEntity;
import com.cjss.student.model.Student;
import com.cjss.student.service.StudentFeeService;
import com.cjss.student.service.StudentMarksService;
import com.cjss.student.service.StudentService;
import com.cjss.student.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    StudentMarksService studentMarksService;
    @Autowired
    StudentFeeService studentFeeService;
    @Autowired
    SubjectService subjectService;

    @PostMapping("/add-student-details")
    public StudentEntity addStudentDetails(@RequestBody Student student){
        return studentService.addStudentDetails(student);
    }
    @DeleteMapping("/delete-student/{id}")
    public void deleteStudent(@PathVariable Integer id){
        studentService.deleteStudentById(id);
    }

    @PostMapping("/get-students-by-year")
    public List<StudentEntity> getStudentsByYear(Integer year){
        return studentService.getStudentsByYear(year);
    }

    @GetMapping("/get-students-fee-details")
    public List<String> getStudentsFeeDetails(){
        return studentService.getStudentsFeeDetails();
    }

    @GetMapping("/get-students-marks-details")
    public List<String> getStudentMarksDetails(){
        return studentService.getStudentMarksDetails();
    }

    @GetMapping("/get-students-marks-details-order-by-year")
    public List<String> getStudentsByYear(){
        return studentService.getStudentMarksDetailsOrderByYear();
    }
}



