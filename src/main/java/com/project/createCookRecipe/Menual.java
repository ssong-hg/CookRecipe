package com.project.createCookRecipe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.project.qna.question.Question;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Menual {
	@Id
	@SequenceGenerator(name = "MENUAL_SEQ", sequenceName = "menual_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MENUAL_SEQ")
	private Long menualFileId;
		
	@Column(length = 1000)
	private String fullName;
			
	private Long rcpSeq;
}
