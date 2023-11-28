package com.innovaturelabs.training.examportal.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.innovaturelabs.training.examportal.entity.Question;
import com.innovaturelabs.training.examportal.exception.NotFoundException;
import com.innovaturelabs.training.examportal.form.QuestionForm;
import com.innovaturelabs.training.examportal.view.QuestionDetailView;
import com.innovaturelabs.training.examportal.view.QuestionListView;
@Service
public interface QuestionService {
	
	Collection<QuestionListView> list();
	
	QuestionDetailView add(QuestionForm form,Integer examId);
	
	public Collection<QuestionListView> get(Integer examId) throws NotFoundException;
	
	QuestionDetailView update(Integer questionId,QuestionForm form)throws NotFoundException;
	
	void delete(Integer questionId) throws NotFoundException;
	
	Question  getQuestion(Integer questionId);
	
	Map<String, Float> evalexam (List<Map> useranswer,Integer examId);
}

