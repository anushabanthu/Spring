package com.cjss.employee.controller;
import com.cjss.employee.service.EmployeeService;
import com.cjss.employee.service.BenefitService;
import com.cjss.employee.service.LocationService;
import com.cjss.employee.service.DepartmentService;
import com.cjss.employee.model.Location;
import com.cjss.employee.model.Benefit;
import com.cjss.employee.model.Employee;
import com.cjss.employee.model.Department;
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
    @Autowired
    DepartmentService departmentService;

//    Benefits
    @GetMapping("/get-benefits")
    public List<Benefit> getBenefits(){
        System.out.println("EmployeeController: getBenefits");
        return benefitService.getBenefits();
    }
    @PostMapping("/add-benefit")
    public void addBenefit(@RequestBody Benefit benefit){
        System.out.println("EmployeeController: addBenefits");
        benefitService.addBenefit(benefit);
    }
    @DeleteMapping("/delete-benefit/{id}")
    public void deleteBenefit(@PathVariable int id){
        System.out.println("EmployeeController: deleteBenefits");
        benefitService.deleteBenefitById(id);
    }
//    Locations
    @GetMapping("/get-locations")
    public List<Location> getLocations(){
        System.out.println("EmployeeController: getLocations");
        return locationService.getLocations();
    }
    @PostMapping("/add-location")
    public void addLocation(@RequestBody Location location){
        System.out.println("EmployeeController: addLocations");
        locationService.addLocations(location);
    }
    @DeleteMapping("/delete-location/{id}")
    public void deleteLocation(@PathVariable int id){
        System.out.println("EmployeeController: deleteLocation");
        locationService.deleteLocationById(id);
    }
//    Departments
    @GetMapping("/get-departments")
    public List<Department> getDepartments(){
        System.out.println("EmployeeController: getDepartments");
        return departmentService.getDepartments();
    }
    @PostMapping("/add-department")
    public void addDepartment(@RequestBody Department department){
        System.out.println("EmployeeController: addDepartment");
        departmentService.addDepartment(department);
    }
    @DeleteMapping("/delete-department/{id}")
    public void deleteDepartment(@PathVariable int id){
        System.out.println("EmployeeController: deleteDepartment");
        departmentService.deleteDepartmentById(id);
    }
//    Employees
    @GetMapping("/get-employees")
    public List<Employee> getEmployees(){
        System.out.println("EmployeeController: getEmployees");
        return employeeService.getEmployees();
    }
    @PostMapping("/add-employee")
    public void addEmployee(@RequestBody Employee employee){
        System.out.println("EmployeeController: addEmployee");
        employeeService.addEmployee(employee);
    }
    @DeleteMapping("/delete-employee/{id}")
    public void deleteEmployee(@PathVariable int id){
        System.out.println("EmployeeController: deleteEmployee");
        employeeService.deleteEmployeeById(id);
    }
    @PostMapping("/get-employees-by-country")
    public List<Employee> getEmployeesByCountry(String country){
        return employeeService.getEmployeesByCountry(country,locationService.getLocations());
    }
    @PostMapping("/get-employees-by-cities")
    public List<Employee> getEmployeesByCities(@RequestBody String[] cities){
        return employeeService.getEmployeesByCities(cities,locationService.getLocations());
    }
    @GetMapping("/get-employees-with-benefits")
    public List<Employee> getEmployeesWithBenefits(){
        return employeeService.getEmployeesWithBenefits(benefitService.getBenefits());
    }
    @GetMapping("/get-employees-details")
    public List<String> getEmployeeDetails(){
        return employeeService.getEmployeeDetails(departmentService.getDepartments(),locationService.getLocations());
    }
    @PostMapping("/get-employees-by-city-and-country")
    public List<Employee> getEmployeesByCityAndCountry(String city, String country){
        return employeeService.getEmployeesByCityAndCountry(city,country,locationService.getLocations());
    }
}



