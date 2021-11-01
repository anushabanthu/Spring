package com.cjss.student.service;

import com.cjss.student.entity.StudentMarksEntity;
import com.cjss.student.model.StudentMarks;
import com.cjss.student.repository.StudentMarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentMarksService {
	@Autowired
	private StudentMarksRepository studentMarksRepository;

	public StudentMarks getStudentMarksDetailsById(Integer id){
		Optional<StudentMarksEntity> studentMarksEntity = studentMarksRepository.findById(id);    //findById is available by default from JPARepository
		StudentMarks studentMarks = new StudentMarks();

		studentMarks.setStudentRollNumber(studentMarksEntity.get().getStudentRollNo());
		studentMarks.setMarks(studentMarksEntity.get().getMarks());
		studentMarks.setStudentYear(studentMarksEntity.get().getStudentYear());
		studentMarks.setSubjectNumber(studentMarksEntity.get().getSubjectNumber());
		return studentMarks;
	}

	public StudentMarksEntity addMarksDetails(StudentMarks studentMarks){
		StudentMarksEntity studentMarksEntity = new StudentMarksEntity();
		studentMarksEntity.setStudentRollNo(studentMarks.getStudentRollNumber());
		studentMarksEntity.setMarks(studentMarks.getMarks());
		studentMarksEntity.setStudentYear(studentMarks.getStudentYear());
		studentMarksEntity.setSubjectNumber(studentMarks.getSubjectNumber());
		return studentMarksRepository.save(studentMarksEntity);
	}

	public void deleteStudentMarksById(Integer id){
		studentMarksRepository.deleteById(id);
	}
}
