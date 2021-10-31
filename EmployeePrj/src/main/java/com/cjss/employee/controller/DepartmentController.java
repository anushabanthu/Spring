package com.cjss.employee.controller;

import com.cjss.employee.entity.DepartmentEntity;
import com.cjss.employee.model.Department;
import com.cjss.employee.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping("/get-dept-details-by-id")
    public Department getDeptDetailsById(Integer id){
        System.out.println("DepartmentController: getDepartments");
        return departmentService.getDeptDetailsById(id);
    }
    @PostMapping("/add-department")
    public DepartmentEntity addDeptDetails(@RequestBody Department department){
        System.out.println("DepartmentController: addDepartments");
        return departmentService.addDeptDetails(department);
    }
    @DeleteMapping("/delete-department/{id}")
    public void deleteDepartment(@PathVariable int id){
        System.out.println("DepartmentController: deleteDepartment");
        departmentService.deleteDepartmentById(id);
    }
}



