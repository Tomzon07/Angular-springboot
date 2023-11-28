package com.innovaturelabs.training.examportal.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.innovaturelabs.training.examportal.exception.NotFoundException;

import com.innovaturelabs.training.examportal.view.UserListView;

@Service
public interface UserListService {
	
	Collection<UserListView> list();

	public Collection<UserListView> get(Integer examId) throws NotFoundException;
	
	
}
