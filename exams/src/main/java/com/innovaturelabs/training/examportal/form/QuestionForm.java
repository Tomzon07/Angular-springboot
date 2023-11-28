package com.innovaturelabs.training.examportal.form;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class QuestionForm {
	
	@NotBlank
	@Size(max = 255)
    private String question;
	@Size(max = 255)
	private String optionsA;
	@Size(max = 255)
	private String optionsB;
	@Size(max = 255)
	private String optionsC;
	@Size(max = 255)
	private String optionsD;
	@Size(max = 255)
	private String answer;

	
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOptionsA() {
		return optionsA;
	}

	public void setOptionsA(String optionsA) {
		this.optionsA = optionsA;
	}

	public String getOptionsB() {
		return optionsB;
	}

	public void setOptionsB(String optionsB) {
		this.optionsB = optionsB;
	}

	public String getOptionsC() {
		return optionsC;
	}

	public void setOptionsC(String optionsC) {
		this.optionsC = optionsC;
	}

	public String getOptionsD() {
		return optionsD;
	}

	public void setOptionsD(String optionsD) {
		this.optionsD = optionsD;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	

}