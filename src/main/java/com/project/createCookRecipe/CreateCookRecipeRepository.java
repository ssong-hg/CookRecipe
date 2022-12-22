package com.project.createCookRecipe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.member.Member;



public interface CreateCookRecipeRepository extends JpaRepository<CreateCookRecipe, Long>{

	CreateCookRecipe findByRcpSeq(Long rcpSeq);
	
	List<CreateCookRecipe> findAll();
	
	List<CreateCookRecipe> findByMember(Member member);
}
