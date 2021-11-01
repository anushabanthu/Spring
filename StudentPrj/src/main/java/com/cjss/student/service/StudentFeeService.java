package com.cjss.student.service;

import com.cjss.student.entity.StudentFeeEntity;
import com.cjss.student.model.StudentFee;
import com.cjss.student.repository.StudentFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentFeeService {
	@Autowired
	private StudentFeeRepository studentFeeRepository;

	public StudentFee getStudentFeeDetailsById(Integer id){
		Optional<StudentFeeEntity> studentFeeEntity = studentFeeRepository.findById(id);    //findById is available by default from JPARepository
		StudentFee studentFee = new StudentFee();

		studentFee.setStudentRollNo(studentFeeEntity.get().getStudentRollNo());
		studentFee.setFeeAmount(studentFeeEntity.get().getFeeAmount());
		studentFee.setDate(studentFeeEntity.get().getDate().toString());
		return studentFee;
	}

	public StudentFeeEntity addFeeDetails(StudentFee studentFee){
		StudentFeeEntity studentFeeEntity = new StudentFeeEntity();
		studentFeeEntity.setStudentRollNo(studentFee.getStudentRollNo());
		studentFeeEntity.setFeeAmount(studentFee.getFeeAmount());
		studentFeeEntity.setDate(studentFee.getDate().toString());
		return studentFeeRepository.save(studentFeeEntity);
	}

	public void deleteStudentFeeById(Integer id){
		studentFeeRepository.deleteById(id);
	}
}
