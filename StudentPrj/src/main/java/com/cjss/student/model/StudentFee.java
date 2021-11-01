package com.cjss.student.model;
import java.time.LocalDate;

public class StudentFee {
	private Integer studentRollNo;
	private int feeAmount;
	private LocalDate date;

	public Integer getStudentRollNo() {
		return studentRollNo;
	}
	public int getFeeAmount() {
		return feeAmount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setStudentRollNo(Integer studentRollNo) {
		this.studentRollNo = studentRollNo;
	}
	public void setFeeAmount(int feeAmount) {
		this.feeAmount = feeAmount;
	}
	public void setDate(String date) {
		this.date = LocalDate.parse(date);
	}
}
