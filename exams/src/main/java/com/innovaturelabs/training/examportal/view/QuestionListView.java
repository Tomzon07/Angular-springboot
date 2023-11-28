package com.innovaturelabs.training.examportal.view;

import java.util.Date;

import com.innovaturelabs.training.examportal.entity.Question;
import com.innovaturelabs.training.examportal.json.Json;

public class QuestionListView {

	private final Integer questionId;
	private final String question;
	private final String optionsA;
	private final String optionsB;
	private final String optionsC;
	private final String optionsD;
	private final String answer;
	private final byte status;

	@Json.DateTimeFormat
	private final Date createDate;
	@Json.DateTimeFormat
	private final Date updateDate;

	public QuestionListView(Integer questionId, String question, String optionsA, String optionsB, String optionsC,
			String optionsD, String answer, byte status, Date createDate, Date updateDate) {

		this.questionId = questionId;
		this.question = question;
		this.optionsA = optionsA;
		this.optionsB = optionsB;
		this.optionsC = optionsC;
		this.optionsD = optionsD;
		this.answer = answer;
		this.status = status;
		this.createDate = createDate;
		this.updateDate = updateDate;

	}
	
	public QuestionListView(Question question ) {
    	this.questionId = question.getQuestionId();
    	this.question = question.getQuestion();
    	this.optionsA = question.getOptionsA();
    	this.optionsB = question.getOptionsB();
    	this.optionsC = question.getOptionsC();
    	this.optionsD =question.getOptionsD();
    	this.answer=question.getAnswer();
    	this.status=question.getStatus();
    	this.createDate=question.getCreateDate();
    	this.updateDate=question.getUpdateDate();
        	
    	
    }
	
	

	public Integer getQuestionId() {
		return questionId;
	}

	public String getQuestion() {
		return question;
	}

	public String getOptionsA() {
		return optionsA;
	}

	public String getOptionsB() {
		return optionsB;
	}

	public String getOptionsC() {
		return optionsC;
	}

	public String getOptionsD() {
		return optionsD;
	}

	public String getAnswer() {
		return answer;
	}

	public byte getStatus() {
		return status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

}