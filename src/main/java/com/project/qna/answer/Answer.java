package com.project.qna.answer;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.project.member.Member;
import com.project.qna.question.Question;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Answer {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANSWER_SEQ")
    @SequenceGenerator(sequenceName = "answer_seq", allocationSize = 1, name = "ANSWER_SEQ")
    private Integer answerNo;
	
	@Column(length = 4000)
	private String content;
	
	
	private LocalDateTime createDate;
	
	@ManyToOne
	private Question question;
	
	@OneToOne
	@JoinColumn(name="memId")
	private Member memId;
	
	
}
