package com.project.admin;


import java.util.List;

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

import com.project.member.LoginInput;
import com.project.member.Member;
import com.project.member.MemberService;
import com.project.qna.answer.Answer;
import com.project.qna.answer.AnswerForm;
import com.project.qna.answer.AnswerService;
import com.project.qna.question.Question;
import com.project.qna.question.QuestionForm;
import com.project.qna.question.QuestionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
@RequiredArgsConstructor
public class AdiminController {

	private final AdminService  adminService;
	private final MemberService memberService;
	private final QuestionService questionService;
	private final AnswerService answerService;
	

	@GetMapping("/master")
	public String admin(Model model, HttpSession session) {
		 Member member = (Member)session.getAttribute("member");
		 
		model.addAttribute("session", session);
		
		return "admin/master";
	}
	
	
	//회원목록 조회
	
	@GetMapping("/member")
	public String member(Model model, HttpSession session) {
		
        
		List<Member> memberList = 
				this.adminService.getMemberList();

		model.addAttribute("memberList",memberList);

		
		return "admin/member";
	}
	
	
	  /*질문리스트*/
    @RequestMapping("/list")    
   
   public String list(Model model,
                       @RequestParam(value="page", defaultValue="0") int page){
    	
        Page<Question> paging = this.adminService.getList(page);
        
        model.addAttribute("paging", paging); /*페이징*/
        
		
        return "admin/answer";
    }

    
    
    /*질문상세*/
    @RequestMapping(value = "/detail/{questionNo}")
    public String detail(Model model, @PathVariable("questionNo") Integer questionNo,
                         AnswerForm answerForm) { /*AnswerForm추가*/
    	
        Question question = this.questionService.getQuestion(questionNo);
        
        model.addAttribute("question", question);
        
        return "admin/answer_detail";
        
    }
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
        return String.format("redirect:/admin/detail/%s", questionNo);
    }
    
    
    //회원이 관리자한테 글남기기
    /*질문등록하기*/
    @GetMapping("/create") /*매개변수로 QuestionForm 객체를 추가*/
    public String adminQuestionCreate(Model model, HttpSession session, QuestionForm questionForm) {
    	
    	Member member =(Member) session.getAttribute("member");
    	model.addAttribute("member", member);
        
    	return "admin/ad_question";
        
    }
        

    //질문등록하기
    @PostMapping("/create") /*QuestionForm 사용할수있게 수정*/
    public String adminQuestionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, HttpSession session) {
    	
    	Member member =(Member) session.getAttribute("member"); // session에 저장된 member 정보를 받아오기
        
    	if (bindingResult.hasErrors()) {
        	
            return "admin/ad_question";
            
        }
        
         //Integer boardNo 1 추가
        adminService.adminQuestionCreate(member, questionForm.getTitle(), questionForm.getContent(),0);
       
        return "admin/success_question";
               
    }
    
    //수정
    
    //관리자가 회원한테 답변작성하는 메서드없음..
    
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
    
    
    
    
    //삭제
    
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


















