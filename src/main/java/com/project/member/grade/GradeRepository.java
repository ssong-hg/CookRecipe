package com.project.member.grade;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;





@Repository
public interface GradeRepository extends JpaRepository<Grade, String>{
	Grade findByGradeNo(String gradeNo);
	Optional<Grade> findByGradeName(String gradeName);
	List<Grade> findAll();
}
