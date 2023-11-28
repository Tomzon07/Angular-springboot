package com.innovaturelabs.training.examportal.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innovaturelabs.training.examportal.service.UserListService;

import com.innovaturelabs.training.examportal.view.UserListView;

@RestController
@RequestMapping("/userlist/question")
public class UserListController {
	
	@Autowired
	private UserListService userListService;
	
	
	@GetMapping("/{examId}")
	public  Collection<UserListView>  get(@PathVariable("examId") Integer examId) {
		return userListService.get(examId);
		}

}
