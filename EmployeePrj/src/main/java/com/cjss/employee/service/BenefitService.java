package com.cjss.employee.service;
import com.cjss.employee.model.Benefit;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BenefitService
{
	private List<Benefit> benefits = new ArrayList<>();
	public void addBenefit(Benefit benefit) {
		benefits.add(benefit);
	}
	public List<Benefit> getBenefits(){
		return benefits;
	}
	public void deleteBenefitById(int id){
		benefits.stream().filter(benefit->benefit.getBenefitId()==id).collect(Collectors.toList()).forEach(each->benefits.remove(each));
	}
}

