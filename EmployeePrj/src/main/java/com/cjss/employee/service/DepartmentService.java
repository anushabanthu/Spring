package com.cjss.employee.service;

import com.cjss.employee.model.Department;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
	private List<Department> departments = new ArrayList<>();
	public static void main(String[] args) {
	}
	public void addDepartments(Department department) {
		departments.add(new Department(department.getDeptId(),department.getDeptName(),department.getLocation()));
	}
	public List<Department> getDepartments(){
		return departments;
	}
	public void deleteDepartmentById(int id){
		departments.stream().filter(dept->dept.getDeptId()==id).collect(Collectors.toList()).forEach(each->departments.remove(each));
	}
}
