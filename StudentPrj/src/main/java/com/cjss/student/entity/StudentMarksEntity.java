package com.cjss.student.entity;
import javax.persistence.*;

@Entity
@Table(name="student_marks_entity")
public class StudentMarksEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)       // Id is autogenerated in sequence(1,2,3...).Other values passed as input will be ignored.
	private Integer studentRollNo;
	@Column
	private int studentYear;
	@Column
	private int subjectNumber;
	@Column
	private int marks;

	public Integer getStudentRollNo() {
		return studentRollNo;
	}

	public void setStudentRollNo(Integer studentRollNo) {
		this.studentRollNo = studentRollNo;
	}

	public int getStudentYear() {
		return studentYear;
	}

	public void setStudentYear(int studentYear) {
		this.studentYear = studentYear;
	}

	public int getSubjectNumber() {
		return subjectNumber;
	}

	public void setSubjectNumber(int subjectNumber) {
		this.subjectNumber = subjectNumber;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}
}
