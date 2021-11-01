package com.cjss.student.controller;

import com.cjss.student.entity.SubjectEntity;
import com.cjss.student.model.Subject;
import com.cjss.student.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @PostMapping("/get-subject-details-by-id")
    public Subject getSubjectDetailsById(Integer id){
        return subjectService.getSubjectDetailsById(id);
    }

    @PostMapping("/add-subject-details")
    public SubjectEntity addSubjectDetails(@RequestBody Subject subject){
        return subjectService.addSubjectDetails(subject);
    }

    @DeleteMapping("/delete-subject-details/{id}")
    public void deleteSubjectDetails(@PathVariable int id){
        subjectService.deleteSubjectById(id);
    }
}



