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
        when(employeeService.getEmployeesByCountry("India",locations)).thenReturn(emp);
        when(employeeService.getEmployeesByCities(new String[]{"Hyd","Chennai"},locations)).thenReturn(Arrays.asList(emp2,emp3));
        when(employeeService.getEmployeesWithBenefits(benefits)).thenReturn(Arrays.asList(emp1,emp2,emp3,emp5));
        when(employeeService.getEmployeeDetails(departments,locations)).thenReturn(Arrays.asList("a","b","c"));
        when(employeeService.getEmployeesByCityAndCountry("Hyd","India",locations)).thenReturn(Arrays.asList(emp1,emp3,emp4));
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
        List<Employee> empFromIndia = employeeController.getEmployeesByCountry("India",locations);
        List<Employee> empByCities = employeeController.getEmployeesByCities(new String[]{"Hyd","Chennai"},locations);
        List<Employee> empWithBenefits = employeeController.getEmployeesWithBenefits(benefits);
        List<String> empDetails = employeeController.getEmployeeDetails(departments,locations);
        List<Employee> empFromCityCountry = employeeController.getEmployeesByCityAndCountry("Hyd","India",locations);

        employeeController.addEmployee(emp1);
        List<Employee> allEmployees = employeeController.getEmployees();
        employeeController.deleteEmployee(emp1.getemployeeId());

        employeeController.addBenefit(benefit1);
        List<Benefit> allBenefits = employeeController.getBenefits();
        employeeController.deleteBenefit(benefit1.getBenefitId());

        employeeController.addLocation(location1);
        List<Location> allLocations = employeeController.getLocations();
        employeeController.deleteLocation(location1.getLocationId());

        employeeController.addDepartment(department1);
        List<Department> allDepartments = employeeController.getDepartments();
        employeeController.deleteDepartment(department1.getDeptId());

//        Then
        assertEquals(empFromIndia.size(),5);
        verify(employeeService).getEmployeesByCountry("India",locations);
        verify(employeeService,times(1)).getEmployeesByCountry("India",locations);
        assertEquals(empFromIndia.get(0),emp1);
        assertEquals(empFromIndia.get(3),emp4);

        assertEquals(empByCities.size(),2);
        verify(employeeService).getEmployeesByCities(new String[]{"Hyd","Chennai"},locations);
        verify(employeeService,times(1)).getEmployeesByCountry("India",locations);
        assertEquals(empByCities.get(0),emp2);
        assertEquals(empByCities.get(1),emp3);

        assertEquals(empWithBenefits.size(),4);
        verify(employeeService).getEmployeesWithBenefits(benefits);
        verify(employeeService,times(1)).getEmployeesWithBenefits(benefits);
        assertEquals(empWithBenefits.get(0),emp1);
        assertEquals(empWithBenefits.get(1),emp2);
        assertEquals(empWithBenefits.get(2),emp3);
        assertEquals(empWithBenefits.get(3),emp5);

        assertEquals(empDetails.size(),3);
        verify(employeeService).getEmployeeDetails(departments,locations);
        verify(employeeService,times(1)).getEmployeeDetails(departments,locations);
        assertEquals(empDetails.get(0),"a");
        assertEquals(empDetails.get(1),"b");
        assertEquals(empDetails.get(2),"c");

        assertEquals(empFromCityCountry.size(),3);
        verify(employeeService).getEmployeesByCityAndCountry("Hyd","India",locations);
        verify(employeeService,times(1)).getEmployeesByCityAndCountry("Hyd","India",locations);
        assertEquals(empFromCityCountry.get(0),emp1);
        assertEquals(empFromCityCountry.get(1),emp3);
        assertEquals(empFromCityCountry.get(2),emp4);

        assertEquals(allEmployees.size(),5);
        verify(employeeService).getEmployees();
        verify(employeeService,times(1)).getEmployees();
        assertEquals(allEmployees.get(0),emp1);
        assertEquals(allEmployees.get(3),emp4);

        assertEquals(allBenefits.size(),3);
        verify(benefitService).getBenefits();
        verify(benefitService,times(1)).getBenefits();
        assertEquals(allBenefits.get(0),benefit1);
        assertEquals(allBenefits.get(2),benefit3);

        assertEquals(allLocations.size(),3);
        verify(locationService).getLocations();
        verify(locationService,times(1)).getLocations();
        assertEquals(allLocations.get(0),location1);
        assertEquals(allLocations.get(2),location3);

        assertEquals(allDepartments.size(),3);
        verify(departmentService).getDepartments();
        verify(departmentService,times(1)).getDepartments();
        assertEquals(allDepartments.get(0),department1);
        assertEquals(allDepartments.get(2),department3);
    }
}
