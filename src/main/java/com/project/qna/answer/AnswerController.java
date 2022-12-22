package com.project.qna.answer;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.project.member.Member;
import com.project.qna.question.Question;
import com.project.qna.question.QuestionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
@Slf4j
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

/* 이전코드 @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam String content) {
        Question question = this.questionService.getQuestion(id);
        this.answerService.create(question, content); //답변저장
        return String.format("redirect:/question/detail/%s", id);
    }*/

    @PostMapping("/create/{questionNo}")/*AnswerForm을 사용하도록 컨트롤러 변경*/
    public String createAnswer(Model model, @PathVariable("questionNo") Integer questionNo,
                               @Valid AnswerForm answerForm, BindingResult bindingResult,HttpSession session) {
    	
    	 Member member = (Member) session.getAttribute("member");
        Question question = this.questionService.getQuestion(questionNo);
        if(bindingResult.hasErrors()){
            model.addAttribute("question", question);
            return "/question/question_list";
        }
        this.answerService.create(member,question, answerForm.getContent());
        return String.format("redirect:/question/detail/%s", questionNo);
    }
    @GetMapping("/modify/{answerNo}")
    public String answerModify(HttpSession session, Model model,AnswerForm answerForm, @PathVariable("answerNo") Integer answerNo) {
       
    	model.addAttribute("session",session);
    	Member member =(Member) session.getAttribute("member");
    	Answer answer = this.answerService.getAnswer(answerNo);
        if (!answer.getMemId().getMemId().equals(member.getMemId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        
        answerForm.setContent(answer.getContent());
        return "/answer/answer_form";
    }
    @PostMapping("/modify/{answerNo}")
    public String answerModify(@Valid AnswerForm answerForm,
            HttpSession session, @PathVariable("answerNo") Integer answerNo) {
    	
        Member member = (Member) session.getAttribute("member");
        
        Answer answer = this.answerService.getAnswer(answerNo);
        if (!answer.getMemId().getMemId().equals(member.getMemId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.answerService.modify(answerNo,answerForm.getContent());
        return "redirect:/question/list";
    }
    
    @GetMapping("/delete/{answerNo}")
    public String answerDelete(HttpSession session,Model model,@PathVariable("answerNo") Integer answerNo){
    	
    	Integer questionNo = this.answerService.getQuestionNo(answerNo);
    	model.addAttribute("session",session);
    	Member member =(Member) session.getAttribute("member");
        Answer answer = this.answerService.getAnswer(answerNo);
        if (!answer.getMemId().getMemId().equals(member.getMemId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.answerService.delete(answer);
        
        
        return "redirect:/question/detail/"+questionNo;
    }
    
    @GetMapping("/search/{mem_id}")
    public String deleteAnswer(HttpSession session, Model model,AnswerForm answerForm, @PathVariable("answerNo") Integer answerNo) {
        
    	model.addAttribute("session",session);
    	Member member =(Member) session.getAttribute("member");
    	Answer answer = this.answerService.getAnswer(answerNo);
        if (!answer.getMemId().getMemId().equals(member.getMemId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        
        answerForm.setContent(answer.getContent());
        return "/answer/answer_form";
    }
}
