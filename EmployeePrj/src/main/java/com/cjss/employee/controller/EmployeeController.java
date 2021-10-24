/*
Description: Contains methods to
    ->  get/add/delete location details,benefits,employees
    ->  get employee from India location
    ->  get employees from Chennai or Hyderabad
    ->  get employee with benefit details
    ->  get employee details
    ->  get employees from Chennai and India
 */
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

import java.util.ArrayList;
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

//    Task1: Get all India Employees
    @GetMapping("/get-india-employees")
    public List<String> getIndiaEmployees(){
        System.out.println("EmployeeController: getIndiaEmployees");
        List<String> indiaEmp = new ArrayList<>();
        employeeService.getEmployees().forEach(employee->{
            locationService.getLocations().stream().filter((location->location.getLocationId()==employee.getLocationId() && location.getLocationCountry()=="India")).forEach(required->indiaEmp.add(employee.getemployeeId()+" "+employee.getEmployeeName()+" "+required.getLocationCountry()));
        });
        return indiaEmp;
    }

//   	Task2 -> Retrieve employees who are in Chennai or Hyderabad
        @GetMapping("/get-chennai-hyd-employees")
        public List<String> getChennaiHydEmployees() {
            System.out.println("EmployeeController: getChennaiHydEmployees");
            List<String> ChennaiHydEmployees =  new ArrayList<>();
            employeeService.getEmployees().forEach(employee -> {
                locationService.getLocations().stream().filter(location -> location.getLocationId() == employee.getLocationId()).
                        filter((locationRequired -> locationRequired.getLocationName() == "Chennai" || locationRequired.getLocationName() == "Hyd")).
                        forEach(required -> ChennaiHydEmployees.add(employee.getemployeeId() + " " + employee.getEmployeeName() + " " + required.getLocationName()));
            });
            return ChennaiHydEmployees;
        }

//      Task3 -> Retrieve employees who have Benefits and display benefits details
        @GetMapping("/get-employees-with-benefits")
        public List<String> getEmployeesWithBenefits(){
            System.out.println("EmployeeController: getEmployeesWithBenefits");
            List<String> empBenefitDetails =  new ArrayList<>();
            employeeService.getEmployees().stream().filter(employee->!employee.getBenefitIds().isEmpty()).forEach(withBenefits->{
                StringBuffer str=new StringBuffer();
                withBenefits.getBenefitIds().forEach(benefitId->{
                    benefitService.getBenefits().stream().filter(benefit->benefitId==benefit.getBenefitId()).forEach(required->str.append(required.getBenefitName()+" "));
                });
                empBenefitDetails.add(String.valueOf(withBenefits.getemployeeId())+" "+withBenefits.getEmployeeName()+" "+str.toString());
            });
            return empBenefitDetails;
        }

//        Task4 -> Retrieve employee details -  employeeId, employeeName, Salary, deptName, locationName, locationCountry
    @GetMapping("/get-employee-details")
    public List<String> getEmployeeDetails() {
        System.out.println("EmployeeController: getEmployeeDetails");
        List<String> empDetails = new ArrayList<>();
        StringBuffer str = new StringBuffer();
        employeeService.getEmployees().forEach(employee -> {
            str.delete(0,str.length());
            str.append(employee.getemployeeId() + " " + employee.getEmployeeName() + " " + employee.getSalary() + " ");
            departmentService.getDepartments().stream().filter(dept -> dept.getDeptId() == employee.getDeptId()).forEach(deptsRequired -> str.append(deptsRequired.getDeptName()));
            locationService.getLocations().stream().filter(location -> location.getLocationId() == employee.getLocationId()).forEach(required -> empDetails.add(str+" "+required.getLocationName() + " " + required.getLocationCountry()));
        });
        return empDetails;
    }

//    Task5 -> Retrieve employees who are in Chennai and India
    @GetMapping("/get-chennai-india-employees")
    public List<String> getChennaiIndiaEmployees() {
        System.out.println("EmployeeController: getChennaiIndiaEmployees");
        List<String> chennaiIndiaEmployees = new ArrayList<>();
        employeeService.getEmployees().forEach(employee -> {
            locationService.getLocations().stream().filter(location -> location.getLocationCountry() == "India").filter(indiaCountry -> indiaCountry.getLocationName() == "Chennai").
                    filter(chennaiLocation -> chennaiLocation.getLocationId() == employee.getLocationId()).
                    forEach(required -> chennaiIndiaEmployees.add(employee.getemployeeId() + " " + employee.getEmployeeName() + " " + required.getLocationName() + " " + required.getLocationCountry()));
        });
        return chennaiIndiaEmployees;
    }
}



