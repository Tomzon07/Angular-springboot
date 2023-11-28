package com.innovaturelabs.training.examportal.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.innovaturelabs.training.examportal.entity.Exam;
import com.innovaturelabs.training.examportal.exception.NotFoundException;
import com.innovaturelabs.training.examportal.form.ExamForm;
import com.innovaturelabs.training.examportal.view.ExamDetailView;
import com.innovaturelabs.training.examportal.view.ExamListView;

public interface ExamService {
	
	Collection<ExamListView> list();
	
	ExamDetailView  add(ExamForm form);
	
	ExamDetailView get(Integer examId)throws NotFoundException;
	
	ExamDetailView update(Integer examId,ExamForm form) throws NotFoundException;
	
	void delete(Integer examId)throws NotFoundException;
	
	
}