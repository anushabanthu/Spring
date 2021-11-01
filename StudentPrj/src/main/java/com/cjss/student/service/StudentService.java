package com.cjss.student.service;
import com.cjss.student.entity.*;
import com.cjss.student.model.Student;
import com.cjss.student.repository.StudentFeeRepository;
import com.cjss.student.repository.StudentMarksRepository;
import com.cjss.student.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cjss.student.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private StudentMarksRepository studentMarksRepository;
	@Autowired
	private StudentFeeRepository studentFeeRepository;
	@Autowired
	private SubjectRepository subjectRepository;

	public StudentEntity addStudentDetails(Student student) {
		StudentEntity studentEntity = new StudentEntity();

		studentEntity.setStudentRollNo(student.getStudentRollNo());
		studentEntity.setStudentName(student.getStudentName());
		studentEntity.setStudentBranch(student.getStudentBranch());
		studentEntity.setStudentYear(student.getStudentYear());
		return studentRepository.save(studentEntity);
	}

	public List<StudentEntity> getStudentsByYear(Integer year) {
		return studentRepository.findByStudentYear(year);

	}

	public List<String> getStudentsFeeDetails() {
		 List<StudentEntity> studentEntities = studentRepository.findAll();
		 List<StudentFeeEntity> studentFeeEntities = studentFeeRepository.findAll();
		 List<String> studentFeeDetails = new ArrayList<>();
		studentEntities.forEach(student -> {
			studentFeeEntities.stream().filter(studentfee -> studentfee.getStudentRollNo() == student.getStudentRollNo()).forEach(required -> studentFeeDetails.add(student.getStudentRollNo() + " " + student.getStudentName() + " " + student.getStudentBranch()+"   " + required.getFeeAmount() + "     " + required.getDate()));
		});
		return studentFeeDetails;
	}

	public List<String> getStudentMarksDetails() {
		List<StudentEntity> studentEntities = studentRepository.findAll();
		List<StudentMarksEntity> studentMarksEntities = studentMarksRepository.findAll();
		List<SubjectEntity> subjectEntities = subjectRepository.findAll();
		List<String> studentMarksDetails = new ArrayList<>();
		studentEntities.forEach(student -> {
//			List<StudentMarksEntity> marksRequired = studentMarksEntities.stream().filter(studentMarks -> studentMarks.getStudentRollNo() == student.getStudentRollNo()).collect(Collectors.toList());
			studentMarksEntities.stream().filter(studentMarks -> studentMarks.getStudentRollNo() == student.getStudentRollNo()).forEach(studentMarks -> {
				subjectEntities.stream().filter(subject -> subject.getSubjectNumber() == studentMarks.getSubjectNumber()).
						forEach(required -> studentMarksDetails.add(student.getStudentRollNo() + " " + student.getStudentName() + " " + required.getSubjectName() + " " + studentMarks.getMarks()));
			});
		});
		return studentMarksDetails;
	}

	public List<String> getStudentMarksDetailsOrderByYear() {
		List<StudentEntity> studentEntities = studentRepository.findAll();
		List<StudentMarksEntity> studentMarksEntities = studentMarksRepository.findAll();
		List<SubjectEntity> subjectEntities = subjectRepository.findAll();
		List<String> studentMarksDetails = new ArrayList<>();
		List<StudentEntity> studentEntitiesSortedByYear = studentEntities.stream().sorted(Comparator.comparingInt(StudentEntity::getStudentYear)).collect(Collectors.toList());
		studentEntitiesSortedByYear.forEach(student -> {
//			List<StudentMarksEntity> marksRequired = studentMarksEntities.stream().filter(studentMarks -> studentMarks.getStudentRollNo() == student.getStudentRollNo()).collect(Collectors.toList());
			studentMarksEntities.stream().filter(studentMarks -> studentMarks.getStudentRollNo() == student.getStudentRollNo()).forEach(studentMarks -> {
				subjectEntities.stream().filter(subject -> subject.getSubjectNumber() == studentMarks.getSubjectNumber()).
						forEach(required -> studentMarksDetails.add(student.getStudentRollNo() + " " + student.getStudentName() + " " + required.getSubjectName() + " " + studentMarks.getMarks()));
			});
		});
		return studentMarksDetails;
	}

	public void deleteStudentById(Integer id){
		studentRepository.deleteById(id);
	}
}
