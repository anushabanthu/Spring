package com.cjss.employee.entity;

import javax.persistence.*;

@Entity
@Table(name="benefit_details")
public class BenefitEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)       // Id is autogenerated in sequence(1,2,3...).Other values passed as input will be ignored.
    private Integer benefitId;
    @Column
    private String benefitName;
    @Column
    private String description;

    public Integer getBenefitId() {
        return benefitId;
    }

    public void setBenefitId(Integer benefitId) {
        this.benefitId = benefitId;
    }

    public String getBenefitName() {
        return benefitName;
    }

    public void setBenefitName(String benefitName) {
        this.benefitName = benefitName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
