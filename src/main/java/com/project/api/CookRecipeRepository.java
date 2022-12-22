package com.project.api;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CookRecipeRepository extends JpaRepository<CookRecipe, String>{
	List<CookRecipe> findByRcpNm(String rcpNm);
	List<CookRecipe> findByRcpWay2(String rcpWay2);
	List<CookRecipe> findByRcpPat2(String rcpPat2);
	List<CookRecipe> findByRcpPartsDtls(String rcpPartsDtls);
	List<CookRecipe> findByRcpPartsDtlsContaining(String rcpPartsDtls);
	List<CookRecipe> findAll();
	
	CookRecipe findByRcpSeq(String rcpSeq);
	Page<CookRecipe> findAll(Pageable pageable);
	Page<CookRecipe> findByRcpWay2(String rcpWay2, Pageable pageable);
	Page<CookRecipe> findByRcpPat2(String rcpPat2, Pageable pageable);
	Page<CookRecipe> findByRcpPartsDtlsContaining(String rcpPartsDtls, Pageable pageable);
}
