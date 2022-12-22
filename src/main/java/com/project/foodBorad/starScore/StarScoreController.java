package com.project.foodBorad.starScore;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.member.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StarScoreController {
	
	@Autowired
	StarScoreService starScoreService;
	
	@GetMapping("/starscore")
	@ResponseBody
	public String StarScore (HttpSession session, Model model, @RequestParam("rcpSeq")String rcpSeq, @RequestParam("score")String score) {
		Member member =(Member) session.getAttribute("member");
		int rcpSeqNo = Integer.parseInt(rcpSeq);
		int star = Integer.parseInt(score);
		
		log.info("@@@@@@@@@@@@@@@@" + rcpSeq);
		
		
		log.info("########################## 별점 1차 진입" );
		if(this.starScoreService.getStarSore(member, rcpSeqNo) != null) {
			log.info("#######################" + this.starScoreService.getStarSore(member, rcpSeqNo).getStarScoreNo());
			return "notNull";		
		}
		if(rcpSeqNo<10000) {
			log.info("########################## 별점 2차 진입" );
			Integer cookRecipe = rcpSeqNo;

			starScoreService.setStarScoreForCookRecipe(member, cookRecipe, star);
			return "cookRecipe";
		}else {
			log.info("########################## 별점 3차 진입" );
			Integer createCookRecipe = rcpSeqNo;
			starScoreService.setStarScoreForCreateCookRecipe(member, createCookRecipe, star);
			return "createCookRecipe";
		}
		
	}

	
}
