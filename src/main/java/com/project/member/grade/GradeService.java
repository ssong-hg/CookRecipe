package com.project.member.grade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.foodBorad.FoodIngredients;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class GradeService {
	private final GradeRepository gradeRepository;
	
	public Grade getGrade(String gradeNo) {
		
		Grade grade = this.gradeRepository.findByGradeNo(gradeNo);
		
		
		
		return grade;
	}
	
	public Grade getGradeName(String gradeName){
		Optional<Grade> oGrade = this.gradeRepository.findByGradeName(gradeName);
		if(oGrade.isPresent()) {
			Grade grade = oGrade.get();
			
			return grade;
		}
		
		return null;
	}
	
	public void setGrade() {
		List<Grade> gradeList = new ArrayList<Grade>();
		gradeList.add(new Grade("BRONZE","01"));
		gradeList.add(new Grade("SILVER","02"));
		gradeList.add(new Grade("GOLD","03"));
		gradeList.add(new Grade("DIAMOND","04"));
		gradeList.add(new Grade("admin","10"));
		
		
		for(int i = 0; i<gradeList.size();i++) {
			gradeRepository.save(gradeList.get(i));
		}
	}
}
