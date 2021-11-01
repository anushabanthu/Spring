package com.cjss.student.repository;

import com.cjss.student.entity.StudentMarksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMarksRepository extends JpaRepository<StudentMarksEntity, Integer> {

}
