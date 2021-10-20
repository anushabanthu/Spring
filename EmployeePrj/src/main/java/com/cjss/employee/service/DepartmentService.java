package com.cjss.employee.service;

import com.cjss.employee.model.Department;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
	static private List<Department> departments = new ArrayList<>();
	public static void main(String[] args) {
	}
	public void addDepartments(Department department) {
		departments.add(new Department(department.getDeptId(),department.getDeptName(),department.getLocation()));
	}
	public List<Department> getDepartments(){
		return departments;
	}
	public void deleteDepartmentById(int id){
		int count=0;
		for(Department d:departments){
			if(d.getDeptId() == id){
				break;
			}
			count++;
		}
		departments.remove(count);
	}
}
