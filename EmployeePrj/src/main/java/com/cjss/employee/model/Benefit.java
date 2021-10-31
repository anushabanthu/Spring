package com.cjss.employee.model;

import java.util.Objects;

public class Benefit {
	private int benefitId;
	private String benefitName;
	private String description;

	public int getBenefitId() {
		return benefitId;
	}
	public String getBenefitName() {
		return benefitName;
	}
	public String getDescription() {
		return description;
	}
	public void setBenefitId(int benefitId) {
		this.benefitId = benefitId;
	}
	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
