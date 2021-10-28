package com.cjss.employee.service;

import com.cjss.employee.model.Benefit;
import com.cjss.employee.model.Department;
import com.cjss.employee.model.Employee;
import com.cjss.employee.model.Location;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
	private List<Employee> employees = new ArrayList<>();
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	public List<Employee> getEmployees(){
		return employees;
	}
	public Employee getEmployeeById(int id){
		List<Employee> emps = employees.stream().filter(emp->emp.getemployeeId()==id).collect(Collectors.toList());
		return emps.isEmpty()?null:emps.get(0);
	}
	public void deleteEmployeeById(int id){
		employees.stream().filter(emp->emp.getemployeeId()==id).collect(Collectors.toList()).forEach(each->employees.remove(each));
	}
	public List<Employee> getEmployeesByCountry(String country,List<Location> locations){
		List<Employee> emp = new ArrayList<>();
		employees.forEach(employee->{
			locations.stream().filter(location->location.getLocationId()==employee.getLocationId()).filter(idMatched->idMatched.getLocationCountry().equals(country)).forEach(required->emp.add(employee));
		});
		return emp;
	}
	public List<Employee> getEmployeesByCities(String[] cities,List<Location> locations) {
		List<Employee> employeesByCities =  new ArrayList<>();
		employees.forEach(employee -> {
		locations.stream().filter(location -> location.getLocationId() == employee.getLocationId()).
		forEach(idMatched -> Arrays.stream(cities).filter(city -> city == idMatched.getLocationName()).forEach(required -> employeesByCities.add(employee)));
		});
		return employeesByCities;
	}

	public List<Employee> getEmployeesWithBenefits(List<Benefit> benefits){
		List<Employee> empWithBenefits =  new ArrayList<>();
		employees.stream().filter(employee->!employee.getBenefitIds().isEmpty()).forEach(withBenefits->{
			withBenefits.getBenefitIds().forEach(benefitId->{
				benefits.stream().filter(benefit->benefitId==benefit.getBenefitId()).forEach(required->empWithBenefits.add(withBenefits));
			});
		});
		return empWithBenefits;
	}
	public List<String> getEmployeeDetails(List<Department> departments,List<Location> locations) {
		List<String> empDetails = new ArrayList<>();
		StringBuffer str = new StringBuffer();
		employees.forEach(employee -> {
			str.delete(0,str.length());
			str.append(employee.getemployeeId() + " " + employee.getEmployeeName() + " " + employee.getSalary() + " ");
			departments.stream().filter(dept -> dept.getDeptId() == employee.getDeptId()).forEach(deptsRequired -> str.append(deptsRequired.getDeptName()));
			locations.stream().filter(location -> location.getLocationId() == employee.getLocationId()).forEach(required -> empDetails.add(str+" "+required.getLocationName() + " " + required.getLocationCountry()));
		});
		return empDetails;
	}
	public List<Employee> getEmployeesByCityAndCountry(String city, String country, List<Location> locations) {
		List<Employee> employeesByCityAndCountry = new ArrayList<>();
		employees.forEach(employee -> {

			locations.stream().filter(location -> location.getLocationCountry().equals(country)).filter(indiaCountry -> indiaCountry.getLocationName().equals(city)).
					filter(chennaiLocation -> chennaiLocation.getLocationId() == employee.getLocationId()).
					forEach(required -> employeesByCityAndCountry.add(employee));
		});
		return employeesByCityAndCountry;
	}
}
