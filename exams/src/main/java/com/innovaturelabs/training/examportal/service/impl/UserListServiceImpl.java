package com.innovaturelabs.training.examportal.service.impl;


import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovaturelabs.training.examportal.exception.NotFoundException;
import com.innovaturelabs.training.examportal.repository.UserListRepository;
import com.innovaturelabs.training.examportal.security.util.SecurityUtil;
import com.innovaturelabs.training.examportal.service.UserListService;
import com.innovaturelabs.training.examportal.view.UserListView;

@Service
public class UserListServiceImpl implements UserListService {
	
	@Autowired
	private UserListRepository userListRepository;
	
    
	public Collection<UserListView> get(Integer examId) throws NotFoundException{
		return userListRepository.findByExamExamId(examId).stream().map(question -> new UserListView(question)).collect(Collectors.toList());
	}


	@Override
	public Collection<UserListView> list() {
		// TODO Auto-generated method stub
		return null;
	}



}