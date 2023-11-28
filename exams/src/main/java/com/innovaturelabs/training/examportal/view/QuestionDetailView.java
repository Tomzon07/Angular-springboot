package com.innovaturelabs.training.examportal.view;

import com.innovaturelabs.training.examportal.entity.Question;

public class QuestionDetailView extends QuestionListView {
	
	public QuestionDetailView (Question question) {
		
		
		super(
				question.getQuestionId(),
				question.getQuestion(),
				question.getOptionsA(),
				question.getOptionsB(),
				question.getOptionsC(),
				question.getOptionsD(),
				question.getAnswer(),
				question.getStatus(),
				question.getCreateDate(),
				question.getUpdateDate()
			  );
		
	}
	

}