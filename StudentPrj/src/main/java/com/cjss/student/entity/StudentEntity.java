package com.cjss.student.entity;
import javax.persistence.*;

@Entity
@Table(name="student_entity")
public class StudentEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)       // Id is autogenerated in sequence(1,2,3...).Other values passed as input will be ignored.
	private Integer studentRollNo;
	@Column
	private String studentName;
	@Column
	private String studentBranch;
	@Column
	private int studentYear;

	public Integer getStudentRollNo() {
		return studentRollNo;
	}

	public void setStudentRollNo(Integer studentRollNo) {
		this.studentRollNo = studentRollNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentBranch() {
		return studentBranch;
	}

	public void setStudentBranch(String studentBranch) {
		this.studentBranch = studentBranch;
	}

	public int getStudentYear() {
		return studentYear;
	}

	public void setStudentYear(int studentYear) {
		this.studentYear = studentYear;
	}
}
