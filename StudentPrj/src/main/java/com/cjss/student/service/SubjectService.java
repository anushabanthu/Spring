package com.cjss.student.service;

import com.cjss.student.entity.SubjectEntity;
import com.cjss.student.model.Subject;
import com.cjss.student.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectService {
	@Autowired
	private SubjectRepository subjectRepository;

	public Subject getSubjectDetailsById(Integer id){
		Optional<SubjectEntity> subjectEntity = subjectRepository.findById(id);    //findById is available by default from JPARepository
		Subject subject = new Subject();

		subject.setSubjectName(subjectEntity.get().getSubjectName());
		subject.setSubjectNumber(subjectEntity.get().getSubjectNumber());
		return subject;
	}

	public SubjectEntity addSubjectDetails(Subject subject){
		SubjectEntity subjectEntity = new SubjectEntity();
		subjectEntity.setSubjectNumber(subject.getSubjectNumber());
		subjectEntity.setSubjectName(subject.getSubjectName());
		return subjectRepository.save(subjectEntity);
	}

	public void deleteSubjectById(Integer id){
		subjectRepository.deleteById(id);
	}
}
