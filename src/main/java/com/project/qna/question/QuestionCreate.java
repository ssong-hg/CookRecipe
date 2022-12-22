package com.project.qna.question;

import com.project.member.Member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuestionCreate {
	
	
	private Member member;
	private Integer questionNo;
	
	private String title;
	
	private String content;
	
}

