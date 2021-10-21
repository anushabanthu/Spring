package com.cjss.employee.service;

import com.cjss.employee.model.Benefit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BenefitService {
	private List<Benefit> benefits = new ArrayList<>();
	public static void main(String[] args) {
	}
	public void addBenefit(Benefit benefit) {
		benefits.add(new Benefit(benefit.getBenefitId(),benefit.getBenefitName(),benefit.getDescription()));
	}
	public List<Benefit> getBenefits(){
		return benefits;
	}
	public void deleteBenefitById(int id){
		benefits.stream().filter(benefit->benefit.getBenefitId()==id).collect(Collectors.toList()).forEach(each->benefits.remove(each));
	}
}

