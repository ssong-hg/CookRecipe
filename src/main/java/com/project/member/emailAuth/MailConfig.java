package com.project.member.emailAuth;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

	
	@Bean
	public JavaMailSender javaMailService() {
	
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		
		
		javaMailSender.setHost("smtp.naver.com");
		javaMailSender.setUsername("unnaj15906");
		javaMailSender.setPassword("skdb0928!!");

		//메일 인증서버 포트
        javaMailSender.setPort(465);
        javaMailSender.setJavaMailProperties(getMailProperties()); //메일 인증서버 정보 가져오기
        javaMailSender.setDefaultEncoding("UTF-8");

        return javaMailSender;
		
	}
	
	 private Properties getMailProperties() {
	        Properties properties = new Properties();
	        properties.setProperty("mail.transport.protocol", "smtp");
	        properties.setProperty("mail.smtp.auth", "true");
	        properties.setProperty("mail.smtp.starttls.enable", "true");
	        properties.setProperty("mail.debug", "true");
	        properties.setProperty("mail.smtp.ssl.trust","smtp.naver.com");
	        properties.setProperty("mail.smtp.ssl.enable","true");
	        
	        return properties;
	    }
	
	
	
}
