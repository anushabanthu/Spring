package com.cjss.employee.model;

public class Employee {
	private Integer employeeId;
	private String employeeName;
	private int salary;
	private int locationId;
	private int deptId;
	private String benefits = new String();

	public String getEmployeeName() {
		return employeeName;
	}
	public int getSalary() {
		return salary;
	}
	public int getLocationId() {
		return locationId;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setEmployeeId(Integer employeeId) {

		this.employeeId = employeeId;
	}
	public void setEmployeeName(String employeeName) {

		this.employeeName = employeeName;
	}
	public void setSalary(int Salary) {
		this.salary = Salary;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public String getBenefits() {
		return benefits;
	}

	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}
}
