package com.innovaturelabs.training.examportal.view;


import java.util.Date;

import com.innovaturelabs.training.examportal.entity.Question;
import com.innovaturelabs.training.examportal.json.Json;

public class UserListView {
	
	private final int questionId;
	private final String question;
	private final String optionsA;
	private final String optionsB;
	private final String optionsC;
	private final String optionsD;
	
	public UserListView(Question question ) {
    	this.questionId = question.getQuestionId();
    	this.question = question.getQuestion();
    	this.optionsA = question.getOptionsA();
    	this.optionsB = question.getOptionsB();
    	this.optionsC = question.getOptionsC();
    	this.optionsD =question.getOptionsD();
        	
    	
    }
	
    
    public UserListView(Integer questionId,String question,String optionsA,String optionsB,String optionsC,String optionsD ) {
    	this.questionId = questionId;
    	this.question = question;
    	this.optionsA = optionsA;
    	this.optionsB = optionsB;
    	this.optionsC = optionsC;
    	this.optionsD = optionsD;
        	
    	
    }

	public int getQuestionId() {
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

	
}