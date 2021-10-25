package com.cjss.employee.service;

import com.cjss.employee.model.Benefit;
import com.cjss.employee.model.Department;
import com.cjss.employee.model.Employee;
import com.cjss.employee.model.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;
    @InjectMocks
    private BenefitService benefitService;
    @InjectMocks
    private LocationService locationService;
    @InjectMocks
    private DepartmentService departmentService;

    @Test
    public void testGetEmployeeByDesignation() {

        Employee emp1 = new Employee(101 , "Rama", 10000,2,1, Arrays.asList(1));
        Employee emp2 = new Employee(102 , "",10000,4,1,Arrays.asList(1,2));
        Employee emp3 = new Employee(103 , "anu", 21213,1,2,Arrays.asList());
        Employee emp4 = new Employee(103 , "anu", 21213,1,2,Arrays.asList());
        Employee emp5 = emp1;
        Employee emp6 = new Employee(104 , "anu", 21213,5,2,Arrays.asList());

        Benefit benefit1 = new Benefit(1,"travel","free");
        Benefit benefit2 = new Benefit(2,"food","free");
        Benefit benefit3 = new Benefit(3,"medicine","free");
        Benefit benefit4 = new Benefit(3,"medicine","free");

        Location location1 = new Location(1,"Hyd","India");
        Location location2 = new Location(2,"Bgl","India");
        Location location3 = new Location(3,"Chennai","India");
        Location location4 = new Location(3,"Chennai","India");
        Location location5 = new Location(4,"London","UK");
        Location location6 = new Location(5,"Hyd","ABC");

        Department department1 = new Department(1,"it","Hyd");
        Department department2 = new Department(2,"finance","hyd");
        Department department3 = new Department(3,"rnd","bgl");
        Department department4 = new Department(3,"rnd","bgl");

//        BENEFITS TESTING
        assertTrue(benefitService.getBenefits().size()==0);
        benefitService.addBenefit(benefit1);
        assertTrue(benefitService.getBenefits().size()==1);
        assertTrue(benefitService.getBenefits().get(0).getBenefitId()==1);
        assertTrue(benefitService.getBenefits().get(0).getBenefitName()=="travel");
        assertTrue(benefitService.getBenefits().get(0).getDescription()=="free");
        assertFalse(benefitService.getBenefits().get(0).getDescription()=="no");
        benefitService.addBenefit(benefit2);
        benefitService.addBenefit(benefit3);
        benefitService.addBenefit(benefit4);
        benefitService.getBenefits().forEach(benefit->{
            assertFalse(benefit.getBenefitId()==0);
            assertFalse(benefit.getBenefitName()==null);
            assertFalse(benefit.getDescription()==null);
        });
//        Benefit model equals method is overridden for content comparision. Hence different objs with same content are equal using
//        assertEquals
        assertEquals(benefit3,benefit4);
        assertNotSame(benefit3,benefit4);
//
//        LOCATION TESTING
        assertTrue(locationService.getLocations().size()==0);
        locationService.addLocations(location1);
        assertTrue(locationService.getLocations().size()==1);
        assertTrue(locationService.getLocations().get(0).getLocationId()==1);
        assertTrue(locationService.getLocations().get(0).getLocationName()=="Hyd");
        assertTrue(locationService.getLocations().get(0).getLocationCountry()=="India");
        assertFalse(locationService.getLocations().get(0).getLocationCountry()=="hi");
        locationService.addLocations(location2);
        locationService.addLocations(location3);
        locationService.addLocations(location4);
        locationService.addLocations(location5);
        locationService.addLocations(location6);
        locationService.getLocations().forEach(loc->{
            assertFalse(loc.getLocationId()==0);
            assertFalse(loc.getLocationName()==null);
            assertFalse(loc.getLocationCountry()==null);
        });
//        Location model equals method is not overridden for content comparision. Hence different objs with same content are not equal using
//        assertEquals
        assertNotEquals(location3,location4);
        assertNotSame(location3,location4);

//        DEPARTMENTS TESTING
        assertTrue(departmentService.getDepartments().size()==0);
        departmentService.addDepartment(department1);
        assertTrue(departmentService.getDepartments().size()==1);
        assertTrue(departmentService.getDepartments().get(0).getDeptId()==1);
        assertTrue(departmentService.getDepartments().get(0).getDeptName()=="it");
        assertTrue(departmentService.getDepartments().get(0).getLocation()=="Hyd");
        assertFalse(departmentService.getDepartments().get(0).getLocation()=="hi");
        departmentService.addDepartment(department2);
        departmentService.addDepartment(department3);
        departmentService.addDepartment(department4);
        departmentService.getDepartments().forEach(dept->{
                assertFalse(dept.getDeptId()==0);
                assertFalse(dept.getDeptName()==null);
                assertFalse(dept.getLocation()==null);
        });
//        Department model equals method is overridden not for content comparision. Hence different objs with same content are not equal using
//        assertEquals
        assertNotEquals(department3,department4);
        assertNotSame(department3,department4);

//        EMPLOYEE TESTING
        employeeService.addEmployee(emp1);
        employeeService.addEmployee(emp2);
        employeeService.addEmployee(emp3);
        employeeService.addEmployee(emp4);
        employeeService.addEmployee(emp5);
        employeeService.addEmployee(emp6);
        List<Employee> employees = employeeService.getEmployees();
//      Since equals method is not overridden by Employee model for content comparison, assertEquals compare object references only.
//      Hence assertEquals and assertSame behave the same way.
        assertEquals(emp1, employeeService.getEmployeeById(101));
        assertSame(emp1, employeeService.getEmployeeById(101));
        assertNotEquals(emp3, emp4);
        assertNotSame(emp3,emp4);
        assertEquals(emp1,emp5);
        assertSame(emp1,emp5);
        assertNull(employeeService.getEmployeeById(-1));
        assertNotNull(employees);
        employeeService.deleteEmployeeById(-1); //Since delete is not returning any value,cant test with assert

//        EMPLOYEES BY COUNTRY
        List<Employee> indiaEmployees = employeeService.getEmployeesByCountry("India",locationService.getLocations());
        assertEquals(indiaEmployees.get(0),emp1);
        assertEquals(indiaEmployees.get(1),emp3);
        assertEquals(indiaEmployees.get(2),emp4);
        assertEquals(indiaEmployees.get(3),emp5);
        assertEquals(indiaEmployees.size(),4);
//        EMPLOYEES BY MULTIPLE CITIES
        List<Employee> empByCities = employeeService.getEmployeesByCities(new String[]{"London","Bgl"},locationService.getLocations());
        assertEquals(empByCities.get(0),emp1);
        assertEquals(empByCities.get(1),emp2);
        assertEquals(empByCities.get(2),emp5);
        assertEquals(empByCities.size(),3);
//        EMPLOYEES WITH BENEFITS
        List<Employee> empWithBenefits = employeeService.getEmployeesWithBenefits(benefitService.getBenefits());
        assertEquals(empByCities.get(0),emp1);
        assertEquals(empByCities.get(1),emp2);
        assertEquals(empByCities.get(2),emp5);
        assertEquals(empByCities.size(),3);
//        GET EMPLOYEES
        List<String> empDetails = employeeService.getEmployeeDetails(departmentService.getDepartments(),locationService.getLocations());
        assertEquals(Arrays.stream(empDetails.get(0).split(" ")).collect(Collectors.toList()).get(5), "India");
        assertEquals(Arrays.stream(empDetails.get(1).split(" ")).collect(Collectors.toList()).get(2), "10000");
        assertEquals(Arrays.stream(empDetails.get(3).split(" ")).collect(Collectors.toList()).get(3), "finance");
        assertEquals(Arrays.stream(empDetails.get(0).split(" ")).count(),6);
        assertEquals(empDetails.size(),6);
//        EMPLOYEES BY CITY AND COUNTRY
        List<Employee> empByCityAndCountry = employeeService.getEmployeesByCityAndCountry("Hyd","India",locationService.getLocations());
        assertEquals(empByCityAndCountry.size(),2);
        assertEquals(empByCityAndCountry.get(0),emp3);
        assertEquals(empByCityAndCountry.get(1),emp4);
        empByCityAndCountry = employeeService.getEmployeesByCityAndCountry("Hyd","ABC",locationService.getLocations());
        assertEquals(empByCityAndCountry.size(),1);
        assertEquals(empByCityAndCountry.get(0),emp6);

//        DELETE FUNCTIONALITY TESTING
        employeeService.deleteEmployeeById(102);
        assertNull(employeeService.getEmployeeById(102));
        assertTrue(benefitService.getBenefits().size()==4);
        benefitService.deleteBenefitById(2);
        assertTrue(benefitService.getBenefits().size()==3);
        benefitService.getBenefits().forEach(benefit->assertFalse(benefit.getBenefitId()==2));
        assertTrue(locationService.getLocations().size()==6);
        locationService.deleteLocationById(2);
        assertTrue(locationService.getLocations().size()==5);
        locationService.getLocations().forEach(loc->assertFalse(loc.getLocationId()==2));
        assertTrue(departmentService.getDepartments().size()==4);
        departmentService.deleteDepartmentById(2);
        assertTrue(departmentService.getDepartments().size()==3);
        departmentService.getDepartments().forEach(dept->assertFalse(dept.getDeptId()==2));
    }
}
