package com.project.qna.question;

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

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //QuestionRepository 생성자 생성해줌
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    
    public List<Question> getList(){
    	
        return this.questionRepository.findByBoardNo(2);
        
    }

    /*Question 데이터 조사*/
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
        return this.questionRepository.findByHideAndBoardNo(0,pageable,2);
    }
    public Page<Question> getAdminList(int page,Member member){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.questionRepository.findByBoardNoAndMemId(1,pageable,member);
    }
    public Page<Question> getMemberList(int page,Member member){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.questionRepository.findByBoardNoAndMemId(2,pageable,member);
    }

    //자유게시판에 저장하는 메서드 2번게시판
    /*질문데이터를 저장하는 create메서드*/
    public void create(Member member, String title, String content,Integer hide){
    	
    	
        Question q = new Question();
        q.setMemId(member);
        q.setTitle(title);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now()); 
        q.setHide(hide);
        q.setBoardNo(2);
        this.questionRepository.save(q);
        
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
