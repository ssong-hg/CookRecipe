package com.project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	public String regexEngNum(String input) {
		Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");

		Matcher m = p.matcher(input);
		
		//m.find(): 매치된 값이 있으면 true를, 없으면 false를 반환한다.
		//m.group(): 매치된 값을 반환한다.
		if(m.find() == true) {
			return input;
		}
		return null;
	}
	
	public String regexKorEng(String input) {
		
		Pattern p = Pattern.compile("^[a-zA-Z가-힣]*$");
		

		Matcher m = p.matcher(input);
		
		if(m.find() == true) {
			return input;
		}
		return null;
		
	}
	
	public String regexAdmin(String input) {
		
		Pattern p = Pattern.compile("^(?!)(admin)*$");
		Matcher m = p.matcher(input);
		
		if(m.find() == false) {
			return input;
		}
		
		return null;
	}
	
	public String regexNum(String input) {
		
		Pattern p = Pattern.compile("^[0-9]*$");
		Matcher m = p.matcher(input);
		
		if(m.find() == true) {
			return input;
		}
		
		return null;
	}

	public String regexPw(String input) {
		
		Pattern p = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$");
		Matcher m = p.matcher(input);
		
		if(m.find() == true) {
			return input;
		}
		
		return null;
	}
	
	public String regexName(String input) {
		
		Pattern p = Pattern.compile("^[a-zA-Z가-힣0-9]*$");
		Matcher m = p.matcher(input);
		
		if(m.find() == true) {
			return input;
		}
		
		return null;
	}
	
	
	
	
}
