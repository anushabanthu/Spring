package com.cjss.employee.controller;
import com.cjss.employee.entity.EmployeeEntity;
import com.cjss.employee.model.*;
import com.cjss.employee.service.EmployeeService;
import com.cjss.employee.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    LocationService locationService;
    @Autowired
    EmployeeService employeeService;
    @PostMapping("/get-employee-details-by-id")
    public Employee getEmpDetailsById(Integer id){
        System.out.println("EmployeeController: getEmployees");
        return employeeService.getEmpDetailsById(id);
    }
    @PostMapping("/add-employee")
    public EmployeeEntity addEmpDetails(@RequestBody Employee employee){
        System.out.println("EmployeeController: addEmployee");
        return employeeService.addEmpDetails(employee);
    }
    @DeleteMapping("/delete-employee/{id}")
    public void deleteEmployee(@PathVariable Integer id){
        System.out.println("EmployeeController: deleteEmployee");
        employeeService.deleteEmployeeById(id);
    }

    @PostMapping("/get-employees-by-country")
    public List<Employee> getEmployeesByCountry(String country){
        return employeeService.getEmployeesByCountry(country);
    }

    @PostMapping("/get-employees-by-cities")
    public List<Employee> getEmployeesByCities(@RequestBody String[] cities){
        return employeeService.getEmployeesByCities(cities);
    }

    @GetMapping("/get-employees-with-benefits")
    public List<Employee> getEmployeesWithBenefits(){
        return employeeService.getEmployeesWithBenefits();
    }

    @GetMapping("/get-employees-details")
    public List<String> getEmployeeDetails(){
        return employeeService.getEmployeeDetails();
    }

    @PostMapping("/get-employees-by-city-and-country")
    public List<Employee> getEmployeesByCityAndCountry(String city, String country){
        return employeeService.getEmployeesByCityAndCountry(city,country);
    }
}



