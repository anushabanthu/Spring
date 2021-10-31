package com.cjss.employee.service;
import com.cjss.employee.entity.DepartmentEntity;
import com.cjss.employee.model.Department;
import com.cjss.employee.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;

	public Department getDeptDetailsById(Integer id){
		Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(id);    //findById is available by default from JPARepository

		Department department = new Department();
		department.setDeptId(departmentEntity.get().getDeptId());
		department.setDeptName(departmentEntity.get().getDeptName());
		department.setLocation(departmentEntity.get().getLocation());
		return department;
	}

	public DepartmentEntity addDeptDetails(Department department){
		DepartmentEntity departmentEntity = new DepartmentEntity();

		departmentEntity.setDeptName(department.getDeptName());
		departmentEntity.setLocation(department.getLocation());
		return departmentRepository.save(departmentEntity);
	}

	public void deleteDepartmentById(Integer id){
		departmentRepository.deleteById(id);
	}
}
