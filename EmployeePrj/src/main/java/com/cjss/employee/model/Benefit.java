package com.cjss.employee.model;

import java.util.Objects;

public class Benefit {
	private int benefitId;
	private String benefitName;
	private String description;
	public Benefit(int benefitId,String benefitName,String description) {
		this.benefitId = benefitId;
		this.benefitName = benefitName;
		this.description = description;
	}
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Benefit benefit = (Benefit) o;
		return benefitId == benefit.benefitId && Objects.equals(benefitName, benefit.benefitName) && Objects.equals(description, benefit.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(benefitId, benefitName, description);
	}
}
