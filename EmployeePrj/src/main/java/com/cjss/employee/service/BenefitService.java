package com.cjss.employee.service;
import com.cjss.employee.entity.BenefitEntity;
import com.cjss.employee.model.Benefit;
import com.cjss.employee.repository.BenefitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BenefitService {
	@Autowired
	private BenefitRepository benefitRepository;

	public Benefit getBenefitDetailsById(Integer id){
		Optional<BenefitEntity> benefitEntity = benefitRepository.findById(id);    //findById is available by default from JPARepository

		Benefit benefit = new Benefit();
		benefit.setBenefitId(benefitEntity.get().getBenefitId());
		benefit.setBenefitName(benefitEntity.get().getBenefitName());
		benefit.setDescription(benefitEntity.get().getDescription());
		return benefit;
	}

	public BenefitEntity addBenefitDetails(Benefit benefit){
		BenefitEntity benefitEntity = new BenefitEntity();
		benefitEntity.setBenefitName(benefit.getBenefitName());
		benefitEntity.setDescription(benefit.getDescription());
		return benefitRepository.save(benefitEntity);
	}

	public void deleteBenefitById(Integer id){
		benefitRepository.deleteById(id);
	}
}
