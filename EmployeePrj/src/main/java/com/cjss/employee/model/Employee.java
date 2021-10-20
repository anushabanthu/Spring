package com.cjss.employee.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	private int employeeId;
	private String employeeName;
	private int Salary;
	private int locationId;
	private int deptId;
	List<Integer> benefitId = new ArrayList<>();
	public Employee(int employeeId,String employeeName,int Salary,int locationId,int deptId,List<Integer> benefitId) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.Salary = Salary;
		this.locationId = locationId;
		this.deptId = deptId;
		this.benefitId = benefitId;
	}
	public int getemployeeId() {
		return employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public int getSalary() {
		return Salary;
	}
	public int getLocationId() {
		return locationId;
	}
	public int getDeptId() {
		return deptId;
	}
	public List<Integer> getBenefitIds() {
		return benefitId;
	}
	public void setEmployeeId(int employeeId) {

		this.employeeId = employeeId;
	}
	public void setEmployeeName(String employeeName) {

		this.employeeName = employeeName;
	}
	public void setSalary(int Salary) {
		this.Salary = Salary;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public void setBenefitIds(List<Integer> benefitId) {
		this.benefitId = benefitId;
	}
}
