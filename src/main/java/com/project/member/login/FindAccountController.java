package com.project.member.login;



import java.sql.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.member.Member;
import com.project.member.MemberService;
import com.project.member.emailAuth.RegisterMail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FindAccountController {
	
	private final MemberService memberService;
	private final RegisterMail registerMail;
	
	
	//String code = null;

	
	@GetMapping("/findId")
	public String getFindId() {
		
		return "member/findIdForm";
	}
	
	
	@GetMapping("/getKeyToFindId")
	@ResponseBody
	public String getKey(@RequestParam("nickName") String nickName, @RequestParam("birth") Date birth) throws Exception {
		Member member = memberService.getfindBynickNameAndbirth(nickName, birth);			

		if(member != null) {
			String id = member.getMemId();
			log.info("####id: " + id);
			return id;
		}else {
			log.info("찾으시는 아이디가 존재하지 않습니다");
			return null;
		}
			
		}
			
	
	
	//-----------------------------------------------------------------------------------------
	
	//비밀번호 찾기
	
	@GetMapping("/findPw")
	public String getFindPw() {
		
		return "member/findPwForm";
		
	}
	
	

//	비밀번호 찾기 - 인증 메일 전송
	@GetMapping("/getKeyToFindPw")
	@ResponseBody
	public String getKeyPw(@RequestParam("nickName") String nickName, @RequestParam("memId") String memId) throws Exception {
		
		Member member = memberService.getMemberBynickNameAndMemId(nickName, memId);

		if(member != null) {
			
			String code = registerMail.sendSimpleMessage(memId);
			log.info("####인증코드: " + code);
			return code;
		}
		
		return null;
	}
	
	//인증 메일 확인
	
	@GetMapping("/findPwCheck")
	@ResponseBody
	public String getFindPwCheck(@RequestParam("nickName") String nickName, @RequestParam("memId") String memId, 
			@RequestParam("keyInput") String keyInput, HttpSession session ,@RequestParam("code") String code) throws Exception{

		Member member = memberService.getMemberBynickNameAndMemId(nickName, memId);
		
		
		String result;

		if(!code.equals(keyInput)) {
			result = "unequal";
			
		}else if(member == null) {
			result =  "null";
			
		}else {
			log.info("####nickName, birth로 찾은 member: " + member.getMemId());
			result =  member.getMemId();
			session.setAttribute("member", member);
			
		
		}
		
		return result;
	
		
	}
	

	//------------------------------------------------------------------------------
	//비밀번호 찾기에서 변경
	
	@GetMapping("/changePwFormWithout")
	public String getChangePw(HttpSession session,Model model) {
		
		Member member = (Member)session.getAttribute("member");
		model.addAttribute("member", member);
		log.info("####changePw의 session의 member: " + member.getMemId());
		session.invalidate();
		
		return "member/changePwWithoutOldPw";
	}
	
	
	
	
	@GetMapping("/changeWithoutOldPwCheck")
	@ResponseBody
	public String getchangePw(@RequestParam String inputNewPw, 
			@RequestParam String inputNewPw2,@RequestParam("member") String member) {
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@member :"+member);
		String result = memberService.changeMemberPw(member, inputNewPw, inputNewPw2);
	
		
		return result;
	}
	
	
	
	@GetMapping("/changePwSuccess")
	public String getchangePw(HttpSession session) {
		session.invalidate();

		return "redirect:/login";
	}
	
	
	
	
}
