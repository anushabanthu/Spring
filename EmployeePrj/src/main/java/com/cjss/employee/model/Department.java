package com.cjss.employee.model;

public class Department {
	private int deptId;
	private String deptName;
	private String location;

	public int getDeptId() {
		return deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public String getLocation() {
		return location;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
