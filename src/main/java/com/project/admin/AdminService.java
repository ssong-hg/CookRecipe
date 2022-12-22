package com.project.admin;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.DataNotFoundException;
import com.project.member.Member;
import com.project.member.MemberRepository;
import com.project.qna.answer.AnswerRepository;
import com.project.qna.answer.AnswerService;
import com.project.qna.question.Question;
import com.project.qna.question.QuestionRepository;
import com.project.qna.question.QuestionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminService {


	private final MemberRepository memberRepository;
	private final QuestionRepository questionRepository;
	private final QuestionService questionService;
	private final AnswerRepository answerRepository;
	private final AnswerService answerService;
	

	
	//회원목록 조회
	
	public List<Member> getMemberList(){

		return this.memberRepository.findAll();
	}

	
	//admin question list 

    public List<Question> getList(){
    	
        return this.questionRepository.findByBoardNo(1);
        
    }

    
    /*Question 데이터 조사*/
    //boardNo
    public Question getQuestion(Integer questionNo){
    	
        Optional<Question> question = this.questionRepository.findByQuestionNo(questionNo);
        
        if(question.isPresent()){
            return question.get();
        }else{
            throw new DataNotFoundException("question not found");
        }
    }

    
    
    /*페이징*/
    public Page<Question> getList(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.questionRepository.findByHideAndBoardNo(0,pageable, 1);
    }

    
    
    //관리자 게시판에 저장하는 메서드 1번
    /*질문데이터를 저장하는 create메서드*/
    public void adminQuestionCreate(Member member, String title, String content,Integer hide){
    	
        Question q2 = new Question();
        q2.setMemId(member);
        q2.setTitle(title);
        q2.setContent(content);
        q2.setCreateDate(LocalDateTime.now()); 
        q2.setHide(hide);
        q2.setBoardNo(1);
        this.questionRepository.save(q2);
        
    }
    
    
   
    /* 수정 */
    public void modify(Integer questionNo, String title, String content) {
    	
    	Question question = this.questionRepository.getById(questionNo);
        question.setTitle(title);
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(question);
        
    }
    
    
    
    public void delete(Question question) {
    	
    	
    	this.questionRepository.delete(question);
    }
    
    
    
    public void hidemodify(Question question,Integer hide) {
    	
    	question.setHide(hide);
    	
    	this.questionRepository.save(question);
    }
	
}




















