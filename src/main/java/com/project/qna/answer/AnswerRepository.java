package com.project.qna.answer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.member.Member;

public interface AnswerRepository extends JpaRepository <Answer, Integer>{
	Answer findByAnswerNo(Integer answerNo);
	List<Answer> findByMemId(Member memId);
}
