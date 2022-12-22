package com.project.member.grade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
	@Id
	@NotNull
	@Column(length=100)
	private String gradeName;
	
	@NotNull
	@Column(length=100)
	private String gradeNo;
	
}
