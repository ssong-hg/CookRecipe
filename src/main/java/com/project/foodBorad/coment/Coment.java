package com.project.foodBorad.coment;

import java.time.LocalDateTime;

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
@Entity
@ToString
public class Coment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMENT_SEQ")
    @SequenceGenerator(sequenceName = "coment_seq", allocationSize = 1, name = "COMENT_SEQ")
    private Integer comentNo;
	
	
	private String content;
	
	private LocalDateTime createDate;
	
	
	@ManyToOne
	private CookRecipe cookRecipe;
	
	@ManyToOne
	private CreateCookRecipe createCookRecipe;
	
	@OneToOne
	@JoinColumn(name="memId")
	private Member memId;
	
	
}
