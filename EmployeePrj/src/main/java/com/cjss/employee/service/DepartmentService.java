package com.cjss.employee.service;

import com.cjss.employee.model.Department;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
	private List<Department> departments = new ArrayList<>();
	public static void main(String[] args) {
	}
	public void addDepartment(Department department) {
		departments.add(department);
	}
	public List<Department> getDepartments(){
		return departments;
	}
	public void deleteDepartmentById(int id){
		departments.stream().filter(dept->dept.getDeptId()==id).collect(Collectors.toList()).forEach(each->departments.remove(each));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DepartmentService that = (DepartmentService) o;
		return Objects.equals(departments, that.departments);
	}

	@Override
	public int hashCode() {
		return Objects.hash(departments);
	}
}
