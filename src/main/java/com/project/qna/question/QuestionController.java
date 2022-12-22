package com.project.qna.question;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
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
import com.project.qna.answer.AnswerForm;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question") //URL프리픽스(prefix)
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;
    
    /*질문리스트*/
    @RequestMapping("/list")
    public String list(Model model,
                       @RequestParam(value="page", defaultValue="0") int page){
    	
    	
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging); /*페이징*/
        
		
        return "question/question_list";
    }

    /*질문상세*/
    @RequestMapping(value = "/detail/{questionNo}")
    public String detail(Model model, @PathVariable("questionNo") Integer questionNo,
                         AnswerForm answerForm) { /*AnswerForm추가*/
        Question question = this.questionService.getQuestion(questionNo);
        model.addAttribute("question", question);
        return "/question/question_detail";
    }

    /*질문등록하기*/
    @GetMapping("/create") /*매개변수로 QuestionForm 객체를 추가*/
    public String questionCreate(Model model, HttpSession session, QuestionForm questionForm) {
    	Member member =(Member) session.getAttribute("member");
    	model.addAttribute("member", member);
        return "/question/question_form";
    }

    /*질문등록하기*/
    @PostMapping("/create") /*QuestionForm 사용할수있게 수정*/
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, HttpSession session) {
    	Member member =(Member) session.getAttribute("member"); // session에 저장된 member 정보를 받아오기
        if (bindingResult.hasErrors()) {
            return "question/question_form";
        }
        
        questionService.create(member, questionForm.getTitle(), questionForm.getContent(),0);
        return "redirect:/question/list";
    }
    
    @GetMapping("/modify/{questionNo}")
   
    public String questionModify(HttpSession session, Model model, QuestionForm questionForm, @PathVariable("questionNo") Integer questionNo) {
        
    	
    	model.addAttribute("session", session);
    	Member member =(Member) session.getAttribute("member");
    	Question question = this.questionService.getQuestion(questionNo);
        if(!question.getMemId().getMemId().equals(member.getMemId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
       }
       
        questionForm.setQuestionNo(question.getQuestionNo());
        questionForm.setTitle(question.getTitle());
        questionForm.setContent(question.getContent());
        return "question/question_modify";
    }
    
    
    
    @PostMapping("/modify/{questionNo}")
   
    public String questionModify(@Valid QuestionForm questionForm,
            HttpSession session, @PathVariable("questionNo") Integer questionNo) {
    	
    	Member member =(Member) session.getAttribute("member");
        
        Question question = this.questionService.getQuestion(questionNo);
        if (!question.getMemId().getMemId().equals(member.getMemId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.questionService.modify(questionNo, questionForm.getTitle(), questionForm.getContent());
        return "redirect:/question/list";
    }
    
    @GetMapping("/delete/{questionNo}")
    public String questionDelete(HttpSession session,Model model, @PathVariable("questionNo") Integer questionNo) {
    	
    	
    	
    	model.addAttribute("session", session);
    	Member member =(Member) session.getAttribute("member");
        Question question = this.questionService.getQuestion(questionNo);
        if (!question.getMemId().getMemId().equals(member.getMemId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
     // this.questionService.delete(question);
        this.questionService.hidemodify(question,1);
        return "redirect:/question/list";
    }

}