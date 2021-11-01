package com.cjss.student.model;

public class Student {
	private Integer studentRollNo;
	private String studentName;
	private String studentBranch;
	private int studentYear;
	
	public Integer getStudentRollNo(){ return studentRollNo; }
	public String getStudentName(){
		return studentName;
	}
	public String getStudentBranch(){
		return studentBranch;
	}
	public int getStudentYear(){
		return studentYear;
	}
	public void setStudentRollNo(Integer studentRollNo){
		this.studentRollNo = studentRollNo;
	}
	public void setStudentName(String studentName){
		this.studentName = studentName;
	}
	public void setStudentBranch(String studentBranch){
		this.studentBranch = studentBranch;
	}
	public void setStudentYear(int studentYear){
		this.studentYear = studentYear;
	}

	@Override
	public String toString() {
		return "Student [StudentRollNo=" + studentRollNo + ", StudentName=" + studentName + ", StudentBranch="
				+ studentBranch + ", StudentYear=" + studentYear + "]";
	}
	
}
