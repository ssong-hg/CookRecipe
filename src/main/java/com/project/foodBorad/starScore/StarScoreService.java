package com.project.foodBorad.starScore;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.api.CookRecipe;
import com.project.api.CookRecipeRepository;
import com.project.api.CookRecipeService;
import com.project.createCookRecipe.CreateCookRecipe;
import com.project.createCookRecipe.CreateCookRecipeRepository;
import com.project.createCookRecipe.CreateCookRecipeService;
import com.project.member.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StarScoreService {
	@Autowired
	StarScoreRepository starScoreRepository;
	@Autowired
	CookRecipeRepository cookRecipeRepository;
	@Autowired
	CookRecipeService cookRecipeService;
	@Autowired
	CreateCookRecipeRepository createCookRecipeRepository;
	@Autowired
	CreateCookRecipeService createCookRecipeService;
	
	public List getStarScore(Member member) {
		
		List<StarScore> starScore = this.starScoreRepository.findByMember(member);
		return starScore;
	}
	
	public void setStarScoreForCookRecipe(Member member, Integer rcpSeq, int score) {
		StarScore ss = new StarScore();
		
			String forRcpSeq = Integer.toString(rcpSeq);
			CookRecipe cr = this.cookRecipeService.getCookRecipeForRcpSeq(forRcpSeq);
			ss.setMember(member);
			ss.setRcpSeq(rcpSeq);
			ss.setScore(score);
			starScoreRepository.save(ss);
			List<StarScore> sScore = this.starScoreRepository.findByRcpSeq(rcpSeq);
			int scoreSum = 0;
			for(int i = 0;i<sScore.size();i++) {
				scoreSum += sScore.get(i).getScore();
			}
			log.info("################################## scoreSum :" +scoreSum);;

			double starScoreAvg = scoreSum/sScore.size();
			cr.setStarScoreAvg(starScoreAvg);
			cookRecipeRepository.save(cr);
			
	}
	public void setStarScoreForCreateCookRecipe(Member member, Integer rcpSeq, int score) {
		StarScore ss = new StarScore();
		Long forRcpSeq = Integer.toUnsignedLong(rcpSeq);
		CreateCookRecipe ccr = this.createCookRecipeService.getCreateCookRecipeForRcpSeq(forRcpSeq);
		ss.setMember(member);
		ss.setRcpSeq(rcpSeq);
		ss.setScore(score);
		starScoreRepository.save(ss);
		List<StarScore> sScore = this.starScoreRepository.findByRcpSeq(rcpSeq);
		int scoreSum = 0;
		for(int i = 0;i<sScore.size();i++) {
			scoreSum += sScore.get(i).getScore();
		}
		log.info("################################## scoreSum :" +scoreSum);;
		
		double starScoreAvg = scoreSum/sScore.size();
		ccr.setStarScoreAvg(starScoreAvg);
		createCookRecipeRepository.save(ccr);
	}
	
	public StarScore getStarSore(Member member, Integer rcpSeq) {
		StarScore ss = this.starScoreRepository.findByMemberAndRcpSeq(member, rcpSeq);
		
		return ss;
	}

	public StarScore findByRcpSeq(String rcpSeq) {

		return null;
	}


	
}
