package com.innovaturelabs.training.examportal.view;

import java.util.Date;

import com.innovaturelabs.training.examportal.entity.Exam;

public class ExamDetailView extends ExamListView{
	
	public ExamDetailView(Exam exam) 
	{
		super(
				exam.getExamId(),
				exam.getName(),
				exam.getDescription(),
				exam.getStatus(),
				exam.getCreateDate(),
				exam.getUpdateDate()
			  );
	}	

}
