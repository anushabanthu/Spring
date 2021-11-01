package com.cjss.student.model;

public class StudentMarks {
	private Integer studentRollNumber;
	private Integer studentYear;
	private int subjectNumber;
	private int marks;

	public Integer getStudentRollNumber() {
		return studentRollNumber;
	}
	public Integer getStudentYear() {
		return studentYear;
	}
	public int getSubjectNumber() {
		return subjectNumber;
	}
	public int getMarks() {
		return marks;
	}
	public void setStudentRollNumber(Integer studentRollNumber) {
		this.studentRollNumber = studentRollNumber;
	}
	public void setStudentYear(Integer studentYear) {
		this.studentYear = studentYear;
	}
	public void setSubjectNumber(int subjectNumber) {
		this.subjectNumber = subjectNumber;
	}
	public void setMarks(int studentMarks) {
		this.marks = studentMarks;
	}
}
