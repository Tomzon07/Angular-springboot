package com.innovaturelabs.training.examportal.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.innovaturelabs.training.examportal.entity.Exam;
import com.innovaturelabs.training.examportal.exception.NotFoundException;
import com.innovaturelabs.training.examportal.form.ExamForm;
import com.innovaturelabs.training.examportal.repository.ExamRepository;
import com.innovaturelabs.training.examportal.security.util.SecurityUtil;
import com.innovaturelabs.training.examportal.service.ExamService;
import com.innovaturelabs.training.examportal.view.ExamDetailView;
import com.innovaturelabs.training.examportal.view.ExamListView;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamRepository examRepository;
	
	@Override
	public Collection<ExamListView> list(){
		return examRepository .findAllByStatus(Exam.Status.ACTIVE.value).stream().map(exam->new ExamListView(exam)).collect(Collectors.toList());
	}
	 @Override
	    public ExamDetailView add(ExamForm form) {
	        return new ExamDetailView(examRepository.save(new Exam(form, SecurityUtil.getCurrentUserId())));
	    }

	    @Override
	    public ExamDetailView get(Integer examId) throws NotFoundException {
	        return examRepository.findByExamId(examId)
	        	 .map((exam) -> {
	                 return new ExamDetailView(exam);
	                }).orElseThrow(NotFoundException::new);
	    }

	    @Override
	    @Transactional
	    public ExamDetailView update(Integer examId, ExamForm form) throws NotFoundException {
	        return examRepository.findByExamIdAndUserUserId(examId, SecurityUtil.getCurrentUserId())
	                .map((exam) -> {
	                    return new ExamDetailView(examRepository.save(exam.update(form)));
	                }).orElseThrow(NotFoundException::new);
	    }

//	    @Override
//	    @Transactional
//	    public void delete(Integer examId) throws NotFoundException {
//	    	examRepository.delete(
//	    			examRepository.findByExamIdAndUserUserId(examId, SecurityUtil.getCurrentUserId())
//	                        .orElseThrow(NotFoundException::new)
//	        );
//	    }

	    @Override
	    @Transactional
	    public void delete(Integer examId) {
	    	Exam exam=examRepository.findByExamIdAndStatus(examId,Exam.Status.ACTIVE.value).orElseThrow(NotFoundException::new);
	    	exam.setStatus(Exam.Status.DELETED.value);
	    	exam.setUpdateDate(new Date());
	    	examRepository.save(exam);
	    	System.out.println("inactive");
	    	return;
	    }
		
	}