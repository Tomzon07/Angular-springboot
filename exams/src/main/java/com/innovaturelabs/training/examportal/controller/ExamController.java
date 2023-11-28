package com.innovaturelabs.training.examportal.controller;

import java.security.Principal;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innovaturelabs.training.examportal.form.ExamForm;
import com.innovaturelabs.training.examportal.service.ExamService;
import com.innovaturelabs.training.examportal.view.ExamDetailView;
import com.innovaturelabs.training.examportal.view.ExamListView;


@RestController
@RequestMapping("/exam")
public class ExamController {
	
	@Autowired
	private ExamService examService;
	
    @GetMapping
    public Collection<ExamListView> list(Principal p){
    	return examService.list();
    }
	@PostMapping
	public ExamDetailView add(@Valid @RequestBody ExamForm form) {
		return examService.add(form);
		
	}
	@GetMapping("/{examId}")
	public ExamDetailView get(@PathVariable("examId") Integer examId) {
		return examService.get(examId);
		}
	@PutMapping("/{examId}")
	public ExamDetailView update(@PathVariable("examId") Integer examId, @Valid @RequestBody ExamForm form)
	{
		return examService.update(examId, form);
	
	}
	@DeleteMapping("/{examId}")
	public void delete(@PathVariable("examId") Integer examId) {
		examService.delete(examId);
	}
}
	
	
	
