package com.project.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CookRecipeService {
	@Autowired
	CookRecipeRepository cookrecipeRepository;
	
	public List<CookRecipe> getAllList(){
		
		return this.cookrecipeRepository.findAll();
	}
	
	
	public List<CookRecipe> getCookRecipeForRcpName(String rcpName){
		
		List<CookRecipe> CookRcpForRcpName = this.cookrecipeRepository.findByRcpNm(rcpName);
		
		return CookRcpForRcpName;
		
	}
	
	public List<CookRecipe> getCookRecipeForRcpWay2(String rcpWay2){
		
		List<CookRecipe> CookRcpForRcpRcpWay2 =  this.cookrecipeRepository.findByRcpWay2(rcpWay2);
		
		return CookRcpForRcpRcpWay2;
		
	}
	public List<CookRecipe> getCookRecipeForRcpPat2(String rcpPat2){
		
		List<CookRecipe> CookRcpForRcpPat2 =  this.cookrecipeRepository.findByRcpPat2(rcpPat2);
		
		return CookRcpForRcpPat2;
		
	}
	public List<CookRecipe> getCookRecipeForRcpPartsDtls(String rcpPartsDtls){
		
		List<CookRecipe> CookRcpForRcpPartsDtls =  this.cookrecipeRepository.findByRcpPartsDtls(rcpPartsDtls);
		
		return CookRcpForRcpPartsDtls;
		
	}
	
	public List<CookRecipe> getCookRecipeForRcpPartsDtlsContaining(String rcpPartsDtls){
		
		List<CookRecipe> CookRcpForRcpPartsDtls =  this.cookrecipeRepository.findByRcpPartsDtlsContaining(rcpPartsDtls);
		
		return CookRcpForRcpPartsDtls;
		
	}
	
	
	public CookRecipe getCookRecipeForRcpSeq(String rcpSeq) {
		CookRecipe cr = this.cookrecipeRepository.findByRcpSeq(rcpSeq);
		
		return cr;
	}
	public void updataCookRcp(CookRecipe cookRecipe) {
		cookrecipeRepository.save(cookRecipe);
		
	}
	public Page<CookRecipe> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("readCount"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.cookrecipeRepository.findAll(pageable);
    }
	public Page<CookRecipe> getRcpWay2List(String rcpWay2, int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("readCount"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.cookrecipeRepository.findByRcpWay2(rcpWay2, pageable);
    }
	public Page<CookRecipe> getRcpPat2List(String RcpPat2, int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("readCount"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.cookrecipeRepository.findByRcpPat2(RcpPat2, pageable);
    }
	public Page<CookRecipe> getRcpPartsDtlsList(String rcpPartsDtls, int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("readCount"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.cookrecipeRepository.findByRcpPartsDtlsContaining(rcpPartsDtls, pageable);
    }

}
