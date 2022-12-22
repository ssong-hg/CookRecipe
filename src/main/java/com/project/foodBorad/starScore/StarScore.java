package com.project.foodBorad.starScore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.project.api.CookRecipe;
import com.project.createCookRecipe.CreateCookRecipe;
import com.project.member.Member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class StarScore {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STAR_SCORE_SEQ")
    @SequenceGenerator(sequenceName = "star_score_seq", allocationSize = 1, name = "STAR_SCORE_SEQ")
    private Integer StarScoreNo;
	
	@OneToOne
	@JoinColumn(name="memId")
	private Member member;
	
	private Integer rcpSeq;
	
	private int score;
	
}
