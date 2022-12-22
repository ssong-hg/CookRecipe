package com.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.api.CookRecipe;
import com.project.api.CookRecipeService;
import com.project.member.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	CookRecipeService cookRecipeService;
	
	@GetMapping("/main")
	public String Main(CookRecipe cookrecipe, Model model) {
		CookRecipe cookRcp = new CookRecipe();
		List<CookRecipe> cRecipeList = this.cookRecipeService.getAllList();
		List<CookRecipe> cookList = new ArrayList<CookRecipe>();
		
		for(int i=0 ; i<10 ; i++) {
			Random rd = new Random();
			int getRcpSeq = rd.nextInt(cRecipeList.size());

			cookList.add(cRecipeList.get(getRcpSeq));
			
		}
		
			
		model.addAttribute("cookList",cookList);

		
		
		
		
		
		
//		List<CookRecipe> rc= this.cookRecipeService.getAllList();		
//		for( int i =0; i<rc.size();i++) {
//			if(rc.get(i).getReadCount()==null) {
//				
//				rc.get(i).setReadCount(0);
//				this.cookRecipeService.updataCookRcp(rc.get(i));
//			}
//			
//		} 리스트 새로 받을때 사용
		
		
		return "index";
	}
	
	
	
}
