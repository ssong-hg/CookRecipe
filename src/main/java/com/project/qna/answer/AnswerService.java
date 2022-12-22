package com.project.qna.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.DataNotFoundException;
import com.project.member.Member;
import com.project.qna.question.Question;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;


    public void create(Member member,Question question, String content) {
        Answer answer = new Answer();
        answer.setMemId(member);
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());  //now함수가아닌 newDate()
        answer.setQuestion(question);
        this.answerRepository.save(answer);
    }
    public Answer getAnswer(Integer answerNo) {
        Optional<Answer> answer = this.answerRepository.findById(answerNo);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }
    public Integer getQuestionNo(Integer answerNo) {
    	Answer answer= this.answerRepository.findByAnswerNo(answerNo);
    	
    	Integer questionNo = answer.getQuestion().getQuestionNo();
    	
    	
    	
    	return questionNo;
    }
    
    
    public void modify(Integer answerNo, String content) {
    	Answer answer = this.answerRepository.getById(answerNo);
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }
    public void delete(Answer answer) {
    	
        this.answerRepository.delete(answer);
    }
    
   public void search (Answer answer,Member member) {
	   
   }
   
}
