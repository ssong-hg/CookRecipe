package com.project.member.login;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegMemberForm {

	
	@Size(min=3)
	@Email
	@NotEmpty(message = "이메일은 필수항목입니다.")
	private String memId;
	
	@NotEmpty(message = "이메일 인증은 필수항목입니다.")
	private String confirmEmail;
	
	@NotEmpty(message = "비밀번호는 필수항목입니다.")
	private String memPw;
	
	@NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
	private String pw2;
	
	@NotEmpty
	@Size(max = 10, message = "닉네임은 필수항목입니다.")
	private String nickName;
	
	//NotEmpty 사용X
	@NotNull(message = "생년월일은 필수항목입니다.")
	private Date birth;
	
	@NotEmpty(message = "전화번호는 필수항목입니다.")
	private String tell;
	
	
}
