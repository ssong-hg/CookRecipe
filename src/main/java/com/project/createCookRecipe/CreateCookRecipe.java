package com.project.createCookRecipe;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;

import com.project.foodBorad.coment.Coment;
import com.project.member.Member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
public class CreateCookRecipe {
	@Id
	@SequenceGenerator(sequenceName = "create_cook_recipe_seq", allocationSize = 1, initialValue=10000,name = "CREATE_COOK_RECIPE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREATE_COOK_RECIPE_SEQ")
	private	Long	rcpSeq	; //시퀀스
	
	@OneToMany(mappedBy = "createCookRecipe",
			cascade = CascadeType.REMOVE)
	private List<Coment> comentList;
	
	@ColumnDefault("0")
	private double starScoreAvg;
	
	@Column(length=10)
	@ColumnDefault("0")
	private Integer readCount; //조회수
	@Column(length=2000)
	private	String	rcpNm	; //이름
	@Column(length=2000)
	private	String	rcpWay2	; //요리방법
	@Column(length=2000)
	private	String	rcpPat2	; //요리 분류
	@Column(length=2000)
	private	String	rcpPartsDtls	; //재료 상세
	
	private LocalDateTime createDate;
	@OneToOne
	@JoinColumn(name="memId")
	private Member member;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="rcpSeq")
	private List<MenualText> menualTexts= new ArrayList<MenualText>();
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="rcpSeq")
	private List<Menual> menuals= new ArrayList<Menual>();
	
	@Transient // 테이블에 안들어감
	private String[] files; // [0]은 요리 메인 사진
	
	@Transient // 테이블에 안들어감
	private String[] menualText; // [0]번은 요리 설명
	
	

	
	public void addMenualText(MenualText menualText) {
		menualTexts.add(menualText);
	}

	public void clearMenualText() {
		menualTexts.clear();
	}
	
	public void addMenual(Menual Menual) {
		menuals.add(Menual);
	}

	public void clearMenual() {
		menuals.clear();
	}
}
