package com.project.foodBorad.coment;

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
import org.springframework.web.server.ResponseStatusException;

import com.project.api.CookRecipe;
import com.project.api.CookRecipeService;
import com.project.createCookRecipe.CreateCookRecipe;
import com.project.createCookRecipe.CreateCookRecipeService;
import com.project.member.Member;
import com.project.member.MemberService;
import com.project.qna.answer.AnswerForm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/coment")
@RequiredArgsConstructor
@Controller
@Slf4j
public class ComentController {

    private final CookRecipeService cookRecipeService;
    private final CreateCookRecipeService createCookRecipeService;
    private final ComentService comentService;
    private final MemberService memberService;

    @PostMapping("/create/{rcqSeq}")

    public String createAnswer(Model model, @PathVariable("rcqSeq") String rcqSeq, 
    		@Valid ComentForm comentForm, BindingResult bindingResult,HttpSession session) {
    	
    	log.info(rcqSeq);
    	
    	Member member = (Member) session.getAttribute("member");
        CookRecipe cookRecipe =this.cookRecipeService.getCookRecipeForRcpSeq(rcqSeq);
    
        comentService.create(member, cookRecipe,comentForm.getContent());
        memberService.setMemberGrade(member);
       
        return "redirect:/detail/"+rcqSeq;
        
    }
    
    @GetMapping("/modify/{comentNo}")
    public String answerModify(HttpSession session, Model model,AnswerForm answerForm, @PathVariable("comentNo") Integer comentNo) {
       
    	model.addAttribute("session",session);
    	Member member =(Member) session.getAttribute("member");
    	Coment coment = this.comentService.getComent(comentNo);
        if (!coment.getMemId().getMemId().equals(member.getMemId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        
        answerForm.setContent(coment.getContent());
        this.memberService.setMemberGrade(member);
        return "/answer/answer_form";
    }
    @PostMapping("/modify/{comentNo}")
    public String answerModify(@Valid ComentForm comentForm,
            HttpSession session, @PathVariable("comentNo") Integer comentNo) {
    	String rcqSeq = this.comentService.getRcpSeq(comentNo);
        Member member = (Member) session.getAttribute("member");
        
        Coment coment = this.comentService.getComent(comentNo);
        if (!coment.getMemId().getMemId().equals(member.getMemId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.comentService.modify(comentNo,comentForm.getContent());
        this.memberService.setMemberGrade(member);
        return "redirect:/detail/"+rcqSeq;
    }
    @GetMapping("/delete/{comentNo}")
    public String answerDelete(HttpSession session,Model model,@PathVariable("comentNo") Integer comentNo){
    	
    	String rcqSeq = this.comentService.getRcpSeq(comentNo);
    	model.addAttribute("session",session);
    	Member member =(Member) session.getAttribute("member");
        Coment coment = this.comentService.getComent(comentNo);
        if (!coment.getMemId().getMemId().equals(member.getMemId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.comentService.delete(coment);
        
        
        return "redirect:/detail/"+rcqSeq;
    }
    @PostMapping("/ccreate/{rcqSeq}")

    public String createAnswerC(Model model, @PathVariable("rcqSeq") Long rcqSeq, 
    		@Valid ComentForm comentForm, BindingResult bindingResult,HttpSession session) {
    	
//    	log.info(rcqSeq);
    	
    	Member member = (Member) session.getAttribute("member");
        CreateCookRecipe createCookRecipe =this.createCookRecipeService.getCreateCookRecipeForRcpSeq(rcqSeq);
        if(bindingResult.hasErrors()){
            model.addAttribute("createCookRecipe", createCookRecipe);
            return "/search/search";
        }
        this.comentService.createC(member, createCookRecipe,comentForm.getContent());
        this.memberService.setMemberGrade(member);
       
        return "redirect:/createrecipe/detail/"+rcqSeq;
        
    }
    @GetMapping("/cmodify/{comentNo}")
    public String answerModifyC(HttpSession session, Model model,AnswerForm answerForm, @PathVariable("comentNo") Integer comentNo) {
       
    	model.addAttribute("session",session);
    	Member member =(Member) session.getAttribute("member");
    	Coment coment = this.comentService.getComent(comentNo);
        if (!coment.getMemId().getMemId().equals(member.getMemId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        
        answerForm.setContent(coment.getContent());
        this.memberService.setMemberGrade(member);
        return "/answer/answer_form";
    }
    @PostMapping("/cmodify/{comentNo}")
    public String answerModifyC(@Valid ComentForm comentForm,
            HttpSession session, @PathVariable("comentNo") Integer comentNo) {
    	Long rcqSeq = this.comentService.getRcpSeqC(comentNo);
        Member member = (Member) session.getAttribute("member");
        
        Coment coment = this.comentService.getComent(comentNo);
        if (!coment.getMemId().getMemId().equals(member.getMemId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.comentService.modify(comentNo,comentForm.getContent());
        this.memberService.setMemberGrade(member);
        return "redirect:/createrecipe/detail/"+rcqSeq;
    }
    @GetMapping("/cdelete/{comentNo}")
    public String answerDeleteC(HttpSession session,Model model,@PathVariable("comentNo") Integer comentNo){
    	
    	Long rcqSeq = this.comentService.getRcpSeqC(comentNo);
    	model.addAttribute("session",session);
    	Member member =(Member) session.getAttribute("member");
        Coment coment = this.comentService.getComent(comentNo);
        if (!coment.getMemId().getMemId().equals(member.getMemId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.comentService.delete(coment);
        
        
        return "redirect:/createrecipe/detail/"+rcqSeq;
    }
    
}

