package com.project.qna.question;

import java.time.LocalDateTime;
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

import com.project.member.Member;
import com.project.qna.answer.Answer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString

public class Question {
	//jkhvj
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUESTION_SEQ")
	@SequenceGenerator(sequenceName = "question_seq", allocationSize = 1, name = "QUESTION_SEQ")
    private Integer questionNo;
	
	@Column(length = 1000)
	private String title;
	@Column(length = 4000)
	private String content;
	
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy = "question",
				cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
	
	@OneToOne
	@JoinColumn(name="memId")
	private Member memId;
	
	
	private Integer hide; // 작업 완료전에 null 값 발생으로 오류 뜸 
	
	private Integer boardNo;
	
}
