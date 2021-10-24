package com.cjss.employee.service;

import com.cjss.employee.model.Benefit;
import com.cjss.employee.model.Employee;
import com.cjss.employee.model.Location;
import com.cjss.employee.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
	private List<Employee> employees = new ArrayList<>();
//	@Autowired
	LocationService locationService = new LocationService();
	BenefitService benefitService = new BenefitService();

	public static void main(String[] args) {
	}

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
}
