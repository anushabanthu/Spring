/*
Description: Contains methods to
    ->  get/add/delete location details,benefits,employees
    ->  get employee with location details
    ->  get employee with benefit details
 */
package com.cjss.employee.controller;
import com.cjss.employee.service.EmployeeService;
import com.cjss.employee.service.BenefitService;
import com.cjss.employee.service.LocationService;
import com.cjss.employee.model.Location;
import com.cjss.employee.model.Benefit;
import com.cjss.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    BenefitService benefitService;
    @Autowired
    LocationService locationService;
    @Autowired
    EmployeeService employeeService;

//    Benefits
    @GetMapping("/get-benefits")
    public List<Benefit> getBenefits(){

        return benefitService.getBenefits();
    }
    @PostMapping("/add-benefit")
    public void addBenefit(@RequestBody Benefit benefit){

        benefitService.addBenefit(benefit);
    }
    @DeleteMapping("/delete-benefit/{id}")
    public void deleteBenefit(@PathVariable int id){
        benefitService.deleteBenefitById(id);
    }
//    Locations
    @GetMapping("/get-locations")
    public List<Location> getLocations(){
        return locationService.getLocations();
    }
    @PostMapping("/add-location")
    public void addLocation(@RequestBody Location location){
        locationService.addLocations(location);
    }
    @DeleteMapping("/delete-location/{id}")
    public void deleteLocation(@PathVariable int id){
        locationService.deleteLocationById(id);
    }
//    Employees
    @GetMapping("/get-employees")
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }
    @PostMapping("/add-employee")
    public void addEmployee(@RequestBody Employee employee){
        employeeService.addEmployees(employee);
    }
    @DeleteMapping("/delete-employee/{id}")
    public void deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployeeById(id);
    }

//    Employee Details with location name and location country
    @GetMapping("/get-empLocationDetails")
    public List getEmpLocationDetails(){
        return employeeService.getEmployeesWithLocation();
    }

//    Employee Details with benefits
    @GetMapping("/get-empBenefitsDetails")
    public List getEmpBenefitsDetails(){
        return employeeService.getEmployeesWithBenefits();
    }
}



