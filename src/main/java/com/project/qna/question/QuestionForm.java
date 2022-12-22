package com.project.qna.question;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionForm {
	
	private Integer questionNo;
    @NotEmpty(message = "제목은 필수항목입니다.") /*Null허용치않음*/
    @Size(max=200) /*최대길이200byte*/
    private String title;

    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
}
