package com.project.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.member.emailAuth.RegisterMail;
import com.project.member.grade.GradeService;
import com.project.member.login.LoginForm;
import com.project.member.login.RegMemberForm;

import lombok.extern.slf4j.Slf4j;




@Controller
@Slf4j
public class MemberController {
	
	
	@Autowired
	MemberService memberService;
	@Autowired
	GradeService gradeService;
	@Autowired
	RegisterMail registerMail;
	
	
	@GetMapping("/signup")
	
	public String login(RegMemberForm regMemberForm){
		
		return"member/signupForm";
		
	}
	
	
	@PostMapping("/signup")
	public String postSignup(@Valid RegMemberForm regMemberForm, Member member, HttpServletResponse response, BindingResult bindingResult, Model model) {
		
		
	
		String inputId = regMemberForm.getMemId();
		

		if(inputId == null) {
			
			return "member/error";
		}

		member.setMemId(inputId);

		//암호화
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String securePw = encoder.encode(regMemberForm.getMemPw());
		
		member.setMemPw(securePw);
		member.setNickName(regMemberForm.getNickName());
		member.setBirth(regMemberForm.getBirth());
		member.setTell(regMemberForm.getTell());
		member.setGrade(gradeService.getGrade("01"));
		
		memberService.regMember(member);
		
	
		log.info("####signupServ.regMember(): " + regMemberForm.getMemId());	
		

		//회원가입 성공 시 
		return "index";

	}
	


	
	@GetMapping("/login")
	
	public String getLogin(LoginForm loginFrom) {
		
		log.debug("####입력아이디: " + loginFrom.getMemId());
		log.debug("####입력비밀번호: " + loginFrom.getMemPw());
	
		
		return "member/loginForm";
	}
	
	@PostMapping("/login")

	public String postLogin(LoginForm loginFrom, HttpSession session, Model model) {
	
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Member member = memberService.getMember(loginFrom.getMemId());
		
		if(member == null) {
			log.info("####로그인 실패####");
			return "member/error";
		}
		
		System.out.println(member.getGrade().getGradeName());
		
		
		if (encoder.matches(loginFrom.getMemPw(),member.getMemPw()) == true){
			session.setAttribute("member", member);
		
			//admin login
			if (member.getGrade().getGradeName().equals("admin")) {
				
				session.setAttribute("admin", member);	
				
			} 

			log.info("####로그인 성공####");
			
			return "redirect:/main";
		}else {
			log.info("####로그인실패####");
			return "member/error";

			
		}
		

	}
	
	
	
	@GetMapping("/logout")
	   public String logout(HttpSession session) {
	      Member member = (Member)session.getAttribute("member");
	      
	      System.out.println("member :"+member);
	      
	      session.invalidate();
	      
	      return"redirect:/main";
	   }
	
	
	
	
	//이메일 중복확인
	@GetMapping("/idduplcheck")
	@ResponseBody
	public String idDuplCheck(@RequestParam String inputId) {

		String idDuplCheck;

		//inputId와 동일한 아이디가 있으면 그 member를 리턴

		Member member = memberService.getMember(inputId);
		
		if(inputId.contains("admin") == true || inputId.contains("ADMIN") == true) {
			idDuplCheck = "admin";
		}else if(member == null) {
			idDuplCheck = "possible";
		}else {
			idDuplCheck = "impossible";
		}

		return idDuplCheck;
	}

	
	
	//닉네임 중복확인
	@GetMapping("/nameduplcheck")
	@ResponseBody
	public String nameDuplCheck(@RequestParam String inputNickname) {

		String nameDuplCheck;

		//inputNickname과 동일한 아이디가 있으면 그 member를 리턴

		Member member = memberService.nickName(inputNickname);
		
		if(inputNickname.contains("admin") == true || inputNickname.contains("ADMIN") == true) {
			nameDuplCheck = "admin";
		}else if(member == null) {
			nameDuplCheck = "possible";
		}else {
			nameDuplCheck = "impossible";
		}

		return nameDuplCheck;
	}

	
	//이메일인증
	@GetMapping("/log_email")
	@ResponseBody
	public String mailConfirm(@RequestParam String email)throws Exception {
		String code = registerMail.sendSimpleMessage(email);
		log.info("####controller 인증코드 : "+ code);

		return code;
		
	}
	
	
	
	
	
	
}
