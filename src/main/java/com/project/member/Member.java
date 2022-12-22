package com.project.member;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import com.project.member.grade.Grade;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Member {
	
	@Id
	@Email
	@Column(length=320)
	private String memId;
	
	@Column(length=200)
	private String memPw;
	
	private String tell;
	
	private String nickName;
	@CreatedDate
	private Date birth;
	
	@OneToOne
	@JoinColumn(name="gradeName")
	private Grade grade;
	
	
	
}
