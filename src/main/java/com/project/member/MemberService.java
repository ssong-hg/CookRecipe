 package com.project.member;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.Regex;
import com.project.createCookRecipe.CreateCookRecipeService;
import com.project.foodBorad.coment.ComentService;
import com.project.member.grade.Grade;
import com.project.member.grade.GradeService;
import com.project.member.login.LoginForm;
import com.project.qna.question.QuestionRepository;
import com.project.qna.question.QuestionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {
	private final MemberRepository memberRepository;
	private final QuestionRepository questionRepository;
	private final QuestionService questionService;
	private final CreateCookRecipeService createCookRecipeService;
	private final ComentService comentService;
	private final GradeService gradeService;
	
	
	public Member loginCheck(LoginForm loginForm) {
		
		Optional<Member> oMember = this.memberRepository.findByMemIdAndMemPw(loginForm.getMemId(), loginForm.getMemPw());
		
		if(oMember.isPresent()) {
			Member member = oMember.get();
			
			return member;
		}
		
		return null;
	}

	
	public void regMember(Member member) {
		

		this.memberRepository.save(member);

	}
	
	// Find Id
	
	public Member getfindBynickNameAndbirth(String nickName, Date birth) {

		Optional<Member> oMember = this.memberRepository.findByNickNameAndBirth(nickName, birth);
		if(oMember.isPresent()) {
			Member member = oMember.get();
			
			return member;
		}
		
		
		return null;
	}
	
	//Find Pw

	public Member getMemberBynickNameAndMemId(String nickName, String memId) {

		Optional<Member> oMember = this.memberRepository.findByNickNameAndMemId(nickName, memId);
		if(oMember.isPresent()) {
			Member member = oMember.get();
			
			return member;
		}
		
		
		return null;
	}
	
	
	
	
	

	//아이디 중복확인
	public Member getMember(String MemId) {

		Optional<Member> oMember = this.memberRepository.findById(MemId);
		if(oMember.isPresent()) {
			Member member = oMember.get();
			
			return member;
		}
		
		
		return null;
	}
	
	
	//닉네임 중복확인
	public Member nickName (String nickName) {
		
		Optional<Member> oMember = this.memberRepository.findByNickName(nickName);
		if(oMember.isPresent()) {
			Member member = oMember.get();
			
			return member;
		}
		
		return null;
		
	}
	
	
	

	
	//비밀번호 변경
	
	public String changeMemberPw(HttpSession session, String oldPw, String newPw, String newPw2) {
		
		Regex regex = new Regex();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Member member = (Member)session.getAttribute("member");
		
		if(encoder.matches(oldPw, member.getMemPw()) == true) { //현재 비밀번호 확인
			if(regex.regexPw(newPw) == null) { //비밀번호 양식에 맞지 않는 경우
				return "regex";
				
			}else if(encoder.matches(newPw, member.getMemPw())) {//새 비밀번호가 현재 비밀번호와 같은 경우
				return "same";
				
			}else if(!newPw.equals(newPw2)) {//새 비밀번호 확인 오류
				return "unequal";
				
			}else {
				
				String secureNewPw = encoder.encode(newPw);
				
				
				Optional<Member> oMember = memberRepository.findById(member.getMemId());	
				if(oMember.isPresent()) {
					Member newMember = new Member();
					newMember = oMember.get();
					newMember.setMemPw(secureNewPw);
					
					this.memberRepository.save(newMember);
					session.setAttribute("member", newMember);
				
					return "true";
				}
			}
			
		}
		return "oldPwError";
			
	}
	
	
	//비밀번호 찾기에서 변경
	public String changeMemberPw(String memberId, String newPw, String newPw2) {
		
		Regex regex = new Regex();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		
		if(regex.regexPw(newPw) == null) { //비밀번호 양식에 맞지 않는 경우
			return "regex";
				
		}else if(!newPw.equals(newPw2)) {//새 비밀번호 확인 오류
			return "unequal";
				
		}else {
				
			String secureNewPw = encoder.encode(newPw);
				

			Optional<Member> oMember = memberRepository.findByMemId(memberId);
			if(oMember.isPresent()) {
				log.info("#############member : "+memberId);
				Member newMember = new Member();
				newMember = oMember.get();
				newMember.setMemPw(secureNewPw);
					
				this.memberRepository.save(newMember);
	
				return "true";
			}
		}
		
		return null;
				
	}
	
	
	//닉네임 변경
	public String changeMemberName(HttpSession session, String newName) {
		
		Regex regex = new Regex();
		
		if(regex.regexName(newName) == null) {
			return "regex";
		}
	
		Member sessionMember = (Member)session.getAttribute("member");
		
		Member member = new Member();
		Optional<Member> oMember = memberRepository.findById(sessionMember.getMemId());

		if(oMember.isPresent()) {
			member = oMember.get();
		}
		
		if(member.getNickName().equals(newName)) {
			
			return "same";
			
		}
		
		member.setNickName(newName);
		
		this.memberRepository.save(member);
		session.setAttribute("member", member);
				
		return "true";
		
	}
	

	//전화번호 변경
	
			
	public String changeMemberTell(HttpSession session, String newTell) {

		Regex regex = new Regex();
		
		if(regex.regexNum(newTell) == null) {
			return "regex";
		}

		Member sessionMember = (Member)session.getAttribute("member");
		
		Member member = new Member();
		Optional<Member> oMember = memberRepository.findById(sessionMember.getMemId());
		
		if(oMember.isPresent()) {
			member = oMember.get();
		}
		
		if(member.getTell().equals(newTell)) {
			return "same";
		}
		

		member.setTell(newTell);
					
		this.memberRepository.save(member);
		session.setAttribute("member", member);
				
		return "true";
			
	}
	
	
	public void setMemberGrade(Member member) {
	
		if(createCookRecipeService.getMemberCount(member) >= 20 &&
				comentService.getMemberCount(member)>=40){
				member.setGrade(this.gradeService.getGradeName("DIAMOND"));
				this.memberRepository.save(member);
		}else if(createCookRecipeService.getMemberCount(member) >= 10 &&
				comentService.getMemberCount(member)>=20) {
				member.setGrade(this.gradeService.getGradeName("GOLD"));
				this.memberRepository.save(member);			
		}else if(createCookRecipeService.getMemberCount(member) >= 1 &&
				comentService.getMemberCount(member)>=5) {
			log.info("############### grade :"+gradeService.getGrade("SILVER"));
				member.setGrade(this.gradeService.getGradeName("SILVER"));
				this.memberRepository.save(member);			
		}
	}
	
//	comentService    createCookRecipeService	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
