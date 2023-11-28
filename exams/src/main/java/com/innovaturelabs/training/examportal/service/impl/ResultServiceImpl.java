package com.innovaturelabs.training.examportal.service.impl;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovaturelabs.training.examportal.repository.ResultRepository;
import com.innovaturelabs.training.examportal.security.util.SecurityUtil;
import com.innovaturelabs.training.examportal.service.ResultService;
import com.innovaturelabs.training.examportal.view.ResultListView;

@Service
public class ResultServiceImpl implements ResultService {
	
	
	@Autowired
	private ResultRepository resultRepository;

	@Override
	public Collection<ResultListView> list() {
		System.out.println(SecurityUtil.getCurrentUserId());
		return resultRepository.findAllByUserUserId(SecurityUtil.getCurrentUserId()).stream().map(ResultListView::new).toList();
	}

	@Override
	public Collection<ResultListView> get() {
		return resultRepository.findAll().stream().map(ResultListView::new).toList();
	}
	

}