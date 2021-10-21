package com.cjss.employee.service;

import com.cjss.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
	private List<Employee> employees = new ArrayList<>();
	@Autowired
	LocationService locationService = new LocationService();
	BenefitService benefitService = new BenefitService();

	public static void main(String[] args) {
	}

	public void addEmployees(Employee employee) {
		employees.add(new Employee(employee.getemployeeId(),employee.getEmployeeName(),employee.getSalary(),employee.getLocationId(),employee.getDeptId(),employee.getBenefitIds()));
	}

	public List<Employee> getEmployees(){
		return employees;
	}

	public List<String> getEmployeesWithLocation(){
		List<String> empLocDetails =  new ArrayList<>();
		employees.forEach(employee->{
			empLocDetails.add(employee.getemployeeId()+"		"+employee.getEmployeeName());
			locationService.getLocations().stream().filter(location->location.getLocationId()==employee.getLocationId()).forEach(required->empLocDetails.add(required.getLocationName()+" 	  "+required.getLocationCountry()));
		});
		return empLocDetails;
	}

	public List<String> getEmployeesWithBenefits(){
		List<String> empBenefitDetails =  new ArrayList<>();
		employees.stream().filter(employee->!employee.getBenefitIds().isEmpty()).forEach(withBenefits->{
			empBenefitDetails.add(withBenefits.getemployeeId()+"		"+withBenefits.getEmployeeName() + "		" );
			withBenefits.getBenefitIds().forEach(benefitId->{
				benefitService.getBenefits().stream().filter(benefit->benefitId==benefit.getBenefitId()).forEach(required->empBenefitDetails.add(required.getBenefitName()+" "));
			});
		});
		return empBenefitDetails;
	}

	public void deleteEmployeeById(int id){
		employees.stream().filter(emp->emp.getemployeeId()==id).collect(Collectors.toList()).forEach(each->employees.remove(each));
	}
}
