package com.cjss.student.repository;

import com.cjss.student.entity.StudentFeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentFeeRepository extends JpaRepository<StudentFeeEntity, Integer> {

}
