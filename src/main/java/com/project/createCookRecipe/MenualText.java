package com.project.createCookRecipe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class MenualText {
	@Id
	@SequenceGenerator(name = "MENUAL_TEXT_SEQ", sequenceName = "menual_text_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MENUAL_TEXT_SEQ")
	private Long menualTextId;
		
	@Column(length = 2000)
	private String menualText;
	
	private Long rcpSeq;
			
	
}

