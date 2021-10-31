package com.cjss.employee.service;
import com.cjss.employee.entity.DepartmentEntity;
import com.cjss.employee.entity.EmployeeEntity;
import com.cjss.employee.entity.BenefitEntity;
import com.cjss.employee.entity.LocationEntity;
import com.cjss.employee.model.*;
import com.cjss.employee.repository.BenefitRepository;
import com.cjss.employee.repository.DepartmentRepository;
import com.cjss.employee.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cjss.employee.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private BenefitRepository benefitRepository;

	public Employee getEmpDetailsById(Integer id) {
		Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);    //findById is available by default from JPARepository

		Employee employee = new Employee();

		employee.setEmployeeId(employeeEntity.get().getEmployeeId());
		employee.setEmployeeName(employeeEntity.get().getEmployeeName());
		employee.setDeptId(employeeEntity.get().getDeptId());
		employee.setSalary(employeeEntity.get().getSalary());
		employee.setLocationId(employeeEntity.get().getLocationId());
		employee.setBenefits(employeeEntity.get().getBenefits());

		return employee;
	}

	public EmployeeEntity addEmpDetails(Employee employee) {
		EmployeeEntity employeeEntity = new EmployeeEntity();

		employeeEntity.setEmployeeName(employee.getEmployeeName());
		employeeEntity.setSalary(employee.getSalary());
		employeeEntity.setLocationId(employee.getLocationId());
		employeeEntity.setDeptId(employee.getDeptId());
		employeeEntity.setBenefits(employee.getBenefits());

		return employeeRepository.save(employeeEntity);
	}

	public List<Employee> getEmployeesByCountry(String country) {
		List<EmployeeEntity> employees = new ArrayList<>();
		List<EmployeeEntity> employeesFromDb = employeeRepository.findAll();
		List<LocationEntity> locationsFromDb = locationRepository.findAll();
		employeesFromDb.forEach(employee -> {
			locationsFromDb.stream().filter(location -> location.getLocationId() == employee.getLocationId()).filter(idMatched -> idMatched.getLocationCountry().equals(country)).forEach(required -> employees.add(employee));
		});
		return employeeEntityToModel(employees);
	}

	public List<Employee> getEmployeesByCities(String[] cities) {
		List<EmployeeEntity> employees = new ArrayList<>();
		List<EmployeeEntity> employeesFromDb = employeeRepository.findAll();
		List<LocationEntity> locationsFromDb = locationRepository.findAll();
		employeesFromDb.forEach(employee -> {
			locationsFromDb.stream().filter(location -> location.getLocationId() == employee.getLocationId()).
					forEach(idMatched -> Arrays.stream(cities).filter(city -> city == idMatched.getLocationName()).forEach(required -> employees.add(employee)));
		});
		return employeeEntityToModel(employees);
	}

	public List<Employee> getEmployeesWithBenefits() {
		List<EmployeeEntity> employeesFromDb = employeeRepository.findAll();
		List<BenefitEntity> benefitsFromDb = benefitRepository.findAll();
		List<EmployeeEntity> employees = new ArrayList<>();
		employeesFromDb.stream().filter(employee -> !employee.getBenefits().isEmpty()).forEach(withBenefits -> {
			Arrays.stream(withBenefits.getBenefits().split(",")).forEach(benefitId ->
					benefitsFromDb.stream().filter(benefit -> Integer.valueOf(benefitId) == benefit.getBenefitId()).forEach(required -> employees.add(withBenefits))
			);
		});
		return employeeEntityToModel(employees);
	}

	public List<String> getEmployeeDetails() {
		List<EmployeeEntity> employeesFromDb = employeeRepository.findAll();
		List<DepartmentEntity> departmentsFromDB = departmentRepository.findAll();
		List<LocationEntity> locationsFromDB = locationRepository.findAll();
		List<String> empDetails = new ArrayList<>();
		StringBuffer str = new StringBuffer();
		employeesFromDb.forEach(employee -> {
			str.delete(0, str.length());
			str.append(employee.getEmployeeId() + " " + employee.getEmployeeName() + " " + employee.getSalary() + " ");
			departmentsFromDB.stream().filter(dept -> dept.getDeptId() == employee.getDeptId()).forEach(deptsRequired -> str.append(deptsRequired.getDeptName()));
			locationsFromDB.stream().filter(location -> location.getLocationId() == employee.getLocationId()).forEach(required -> empDetails.add(str + " " + required.getLocationName() + " " + required.getLocationCountry()));
		});
		return empDetails;
	}

	public List<Employee> getEmployeesByCityAndCountry(String city, String country) {
		List<EmployeeEntity> employeesFromDb = employeeRepository.findAll();
		List<LocationEntity> locationFromDb = locationRepository.findAll();
		List<EmployeeEntity> employees = new ArrayList<>();
		employeesFromDb.forEach(employee -> {
			locationFromDb.stream().filter(location -> location.getLocationCountry().equals(country)).
					filter(countryFilter -> countryFilter.getLocationName().equals(city)).
		filter(locationFilter -> locationFilter.getLocationId() == employee.getLocationId()).
					forEach(required -> employees.add(employee));
		});
		return employeeEntityToModel(employees);
	}

	public void deleteEmployeeById(Integer id) {
		employeeRepository.deleteById(id);
	}

	private List<Employee> employeeEntityToModel(List<EmployeeEntity> employeesByEntity) {
		List<Employee> employeesByModel = new ArrayList<>();
		employeesByEntity.forEach(employee -> {
			Employee emp = new Employee();
			emp.setEmployeeId(employee.getEmployeeId());
			emp.setEmployeeName(employee.getEmployeeName());
			emp.setSalary(employee.getSalary());
			emp.setLocationId(employee.getLocationId());
			emp.setDeptId(employee.getDeptId());
			emp.setBenefits(employee.getBenefits());
			employeesByModel.add(emp);
		});
		return employeesByModel;
	}
}
