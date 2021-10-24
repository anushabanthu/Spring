package com.cjss.employee.controller;

import com.cjss.employee.model.Benefit;
import com.cjss.employee.model.Department;
import com.cjss.employee.model.Employee;
import com.cjss.employee.model.Location;
import com.cjss.employee.service.BenefitService;
import com.cjss.employee.service.DepartmentService;
import com.cjss.employee.service.EmployeeService;
import com.cjss.employee.service.LocationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;
    @Mock
    private BenefitService benefitService;
    @Mock
    private LocationService locationService;
    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void testGetDetails() {

        Employee emp1 = new Employee(101 , "Rama", 10000,1,1, Arrays.asList(1));
        Employee emp2 = new Employee(102 , "John",10000,2,1,Arrays.asList(1,2));
        Employee emp3 = new Employee(103 , "anu", 21213,1,2,Arrays.asList(2));
        Employee emp4 = new Employee(103 , "anusha", 21213,1,2,Arrays.asList());
        Employee emp5 = new Employee(104 , "ravi", 11000,3,2,Arrays.asList(1,2));

        Benefit benefit1 = new Benefit(1,"travel","free");
        Benefit benefit2 = new Benefit(2,"food","free");
        Benefit benefit3 = new Benefit(3,"medicine","free");

        Location location1 = new Location(1,"Hyd","India");
        Location location2 = new Location(2,"Bgl","India");
        Location location3 = new Location(3,"Chennai","India");

        Department department1 = new Department(1,"it","Hyd");
        Department department2 = new Department(2,"finance","hyd");
        Department department3 = new Department(3,"rnd","bgl");

        List<Employee> emp = new ArrayList<>();
        emp.add(emp1);
        emp.add(emp2);
        emp.add(emp3);
        emp.add(emp4);
        emp.add(emp5);
        List<Benefit> benefits = new ArrayList<>();
        benefits.add(benefit1);
        benefits.add(benefit2);
        benefits.add(benefit3);
        List<Location> locations = new ArrayList<>();
        locations.add(location1);
        locations.add(location2);
        locations.add(location3);
        List<Department> departments = new ArrayList<>();
        departments.add(department1);
        departments.add(department2);
        departments.add(department3);

        // Given
//        EmployeeService calls inside controller class get mocked with the below implementation.Hence we need to verify if the print statements in controller methods are executed.
//        For example when employeeController.addEmployee is called, check if "EmployeeController: addEmployee" gets printed in output
//        mockito provides dummy implementations to mocked objects(@Mock) which means even if services are not mocked using when,no error will be thrown and the methods return null
        when(employeeService.getEmployees()).thenReturn(emp);
        doNothing().when(employeeService).addEmployee(emp1);
        doNothing().when(employeeService).deleteEmployeeById(emp1.getemployeeId());
        when(benefitService.getBenefits()).thenReturn(benefits);
        doNothing().when(benefitService).addBenefit(benefit1);
        doNothing().when(benefitService).deleteBenefitById(benefit1.getBenefitId());
        when(locationService.getLocations()).thenReturn(locations);
        doNothing().when(locationService).addLocations(location1);
        doNothing().when(locationService).deleteLocationById(location1.getLocationId());
        when(departmentService.getDepartments()).thenReturn(departments);
        doNothing().when(departmentService).addDepartment(department1);
        doNothing().when(departmentService).deleteDepartmentById(department1.getDeptId());

        // When
        employeeController.addEmployee(emp1);
        List<Employee> employees = employeeController.getEmployees();
        System.out.println("Employees list:");
        employees.forEach(employee->System.out.println(employee.getemployeeId()+" "+employee.getEmployeeName()
        +" "+employee.getSalary()+" "+employee.getLocationId()+" "+employee.getBenefitIds()+" "+employee.getDeptId()));
        employeeController.deleteEmployee(emp1.getemployeeId());

        employeeController.addBenefit(benefit1);
        List<Benefit> getBenefits = employeeController.getBenefits();
        System.out.println("Benefits list:");
        getBenefits.forEach(benefit->System.out.println(benefit.getBenefitId()+" "+benefit.getBenefitName()+" "+benefit.getDescription()));
        employeeController.deleteBenefit(benefit1.getBenefitId());

        employeeController.addLocation(location1);
        List<Location> getLocations = employeeController.getLocations();
        System.out.println("Locations list:");
        getLocations.forEach(location->System.out.println(location.getLocationId()+" "+location.getLocationName()+" "+ location.getLocationCountry()));
        employeeController.deleteLocation(location1.getLocationId());

        employeeController.addDepartment(department1);
        List<Department> getDepartments = employeeController.getDepartments();
        System.out.println("Departments list:");
        departments.forEach(department->System.out.println(department.getDeptId()+" "+department.getDeptName()+" "+department.getLocation()));
        employeeController.deleteDepartment(department1.getDeptId());

        // Then
        assertEquals(benefits.get(0).getBenefitId(),1);
        assertEquals(benefits.get(1).getBenefitName(),"food");
        assertEquals(benefits.get(2).getDescription(),"free");
        assertTrue(benefits.size()==3);

        assertEquals(locations.get(0).getLocationId(),1);
        assertEquals(locations.get(1).getLocationName(),"Bgl");
        assertEquals(locations.get(2).getLocationCountry(),"India");
        assertTrue(locations.size()==3);

        assertEquals(departments.get(0).getDeptId(),1);
        assertEquals(departments.get(1).getDeptName(),"finance");
        assertEquals(departments.get(2).getLocation(),"bgl");
        assertTrue(departments.size()==3);

        assertEquals(employees.get(0).getemployeeId(),101);
        assertTrue(employees.size()==5);

//        Employees from India
        List<String> empFromIndia = employeeController.getIndiaEmployees();
        System.out.println(empFromIndia);
        assertTrue(empFromIndia.size()==5);
        assertTrue(empFromIndia.get(0).split(" ")[0].trim().equals("101"));
        assertTrue(empFromIndia.get(1).split(" ")[1].equals("John"));
        assertTrue(empFromIndia.get(2).split(" ")[2].equals("India"));
//        Employees from Chennai or Hyderabad
        List<String> empFromChennaiHyd = employeeController.getChennaiHydEmployees();
        System.out.println(empFromChennaiHyd);
        assertTrue(empFromChennaiHyd.size()==4);
        assertTrue(empFromChennaiHyd.get(0).split(" ")[0].trim().equals("101"));
        assertTrue(empFromChennaiHyd.get(1).split(" ")[1].equals("anu"));
        assertTrue(empFromChennaiHyd.get(2).split(" ")[2].equals("Hyd"));
//        Employees with benefits
        List<String> empWithBenefits = employeeController.getEmployeesWithBenefits();
        System.out.println(empWithBenefits);
        assertTrue(empWithBenefits.size()==4);
        assertTrue(empWithBenefits.get(0).split(" ")[2].trim().equals("travel"));
        assertTrue(empWithBenefits.get(1).split(" ")[2].equals("travel"));
        assertTrue(empWithBenefits.get(1).split(" ")[3].equals("food"));
//        Employee details
        List<String> employeeDetails = employeeController.getEmployeeDetails();
        System.out.println(employeeDetails);
        assertTrue(employeeDetails.size()==5);
        assertTrue(employeeDetails.get(0).split(" ")[0].equals("101")); //ID of 1st employee
        assertTrue(employeeDetails.get(1).split(" ")[4].equals("Bgl")); //Salary of 2nd employee
        assertTrue(employeeDetails.get(2).split(" ")[2].equals("21213")); //LocationName of 3rd employee
//        Chennai India Employees
        List<String> chennaiIndiaEmployees = employeeController.getChennaiIndiaEmployees();
        System.out.println(chennaiIndiaEmployees);
        assertTrue(chennaiIndiaEmployees.size()==1);
        assertTrue(chennaiIndiaEmployees.get(0).split(" ")[2].equals("Chennai")); //ID of 1st employee
    }
}
