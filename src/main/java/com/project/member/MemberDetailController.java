package com.project.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.admin.AdminService;
import com.project.api.CookRecipeService;
import com.project.createCookRecipe.CreateCookRecipe;
import com.project.createCookRecipe.CreateCookRecipeService;
import com.project.qna.question.Question;
import com.project.qna.question.QuestionService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Controller
public class MemberDetailController {
	@Autowired
	MemberService memberService;
	@Autowired
	QuestionService questionService;
	@Autowired
	AdminService adminService;
	@Autowired
	CreateCookRecipeService createCookRecipeService;
	@Autowired
	CookRecipeService cookRecipeService;
	
	public MemberDetailController(MemberService memberService) {
		this.memberService = memberService;
		
	}

	
	//내가 쓴 글 확인하기 자유게시판
	@GetMapping("/boardfree")
	public String boardFree(Model model, HttpSession session, @RequestParam(value="page", defaultValue="0") int page) {
		Member member = (Member)session.getAttribute("member");
		
		Page<Question> paging = this.questionService.getMemberList(page, member);
		model.addAttribute("paging", paging);
		
		
		
		return "member/confirm";
	}
	
	
	//내가 쓴 글 확인하기 관리자
	@GetMapping("/boardadmin")
	public String boardAdmin(Model model, HttpSession session, @RequestParam(value="page", defaultValue="0") int page) {
		
		Member member = (Member)session.getAttribute("member");
		
		Page<Question> paging = this.questionService.getAdminList(page, member);
		
		model.addAttribute("paging", paging);
		
		
		
		return "member/admin_confirm";
	}
	
	
	
	
	//마이페이지
	@GetMapping("/mypage")
	public String mypage(Model model, HttpSession session) {
		
		return "member/mypage";
	}
	
	
	
	//마이페이지 회원정보 수정
	@GetMapping("/memberdetail")
	public String memberDetail(Model model, HttpSession session) {
		
		Member member = new Member();
		member = (Member)session.getAttribute("member");
		
		model.addAttribute("member", member);
	

		return "member/memberDetail";
	}
	
	@GetMapping("/myrecipe")
	public String myCreateRecipe(Model model, HttpSession session) {
		Member member = (Member)session.getAttribute("member");
		List<CreateCookRecipe> ccrList = createCookRecipeService.getCreateCookRecipeListForMember(member);
		log.info("########## ccrList :"+ccrList);
		model.addAttribute("ccrList", ccrList);
		
		return"myPage/myCreateRecipe";
	}
	
	
	
	
	
	//비밀번호 변경
	
	@GetMapping ("/changePwWithOldPw")
	@ResponseBody
	public String getchangePw(@RequestParam String inputOldPw, @RequestParam String inputNewPw, 
			@RequestParam String inputNewPw2, HttpSession session) {
		
		String result = memberService.changeMemberPw(session, inputOldPw, inputNewPw, inputNewPw2);

		return result;
	}
	
	
	//닉네임 변경
	@GetMapping("/changeName")
	@ResponseBody
	public String getchangeName(@RequestParam String inputNewName, HttpSession session) {
		
		String result = memberService.changeMemberName(session, inputNewName);
		
			return result;
	}
		
	
	
	
	//전화번호 변경
	@GetMapping("/changeTell")
	@ResponseBody
	public String getchangeTell(@RequestParam String inputNewTell, HttpSession session) {
		String result = memberService.changeMemberTell(session, inputNewTell);
		
		
		return result;
	}
	
	
	
	
}
