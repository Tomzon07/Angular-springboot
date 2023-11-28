package com.innovaturelabs.training.examportal.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innovaturelabs.training.examportal.entity.Question;
import com.innovaturelabs.training.examportal.form.QuestionForm;
import com.innovaturelabs.training.examportal.service.QuestionService;
import com.innovaturelabs.training.examportal.view.QuestionDetailView;
import com.innovaturelabs.training.examportal.view.QuestionListView;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	

	@PostMapping("/{examId}")
	public QuestionDetailView add(@PathVariable("examId") Integer examId,@Valid @RequestBody QuestionForm form) {
		return questionService.add(form,examId);
		
	}
	@GetMapping("/{examId}")
	public  Collection<QuestionListView>  get(@PathVariable("examId") Integer examId) {
		return questionService.get(examId);
		}
	
	
	@GetMapping("/q/{questionId}")
	public Question  getQuestion(@PathVariable("questionId") Integer questionId) {
		return questionService.getQuestion(questionId);
		}

	@PutMapping("/{questionId}")
	public QuestionDetailView update(@PathVariable("questionId") Integer questionId, @Valid @RequestBody QuestionForm form)
	{
		return questionService.update(questionId, form);
	
	}
	@DeleteMapping("/{questionId}")
	public void delete(@PathVariable("questionId") Integer questionId) {
		questionService.delete(questionId);
	}
	
	@PostMapping("/user/eval/{examId}")
    public  Map<String, Float>  evalexam(@RequestBody List<Map> useranswer,@PathVariable("examId") Integer examId) {
        return questionService.evalexam(useranswer,examId);
      
    }
		
	
}