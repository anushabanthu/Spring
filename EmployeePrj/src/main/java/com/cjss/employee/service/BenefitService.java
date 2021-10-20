package com.cjss.employee.service;

import com.cjss.employee.model.Benefit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BenefitService {
	static private List<Benefit> benefits = new ArrayList<>();
	public static void main(String[] args) {
	}
	public void addBenefit(Benefit benefit) {
		benefits.add(new Benefit(benefit.getBenefitId(),benefit.getBenefitName(),benefit.getDescription()));
	}
	public List<Benefit> getBenefits(){
		return benefits;
	}
	public void deleteBenefitById(int id){
		int count=0;
		for(Benefit b:benefits){
			if(b.getBenefitId() == id){
				break;
			}
			count++;
		}
		benefits.remove(count);
	}
}

