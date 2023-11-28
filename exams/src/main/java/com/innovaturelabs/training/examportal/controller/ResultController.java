package com.innovaturelabs.training.examportal.controller;


import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.innovaturelabs.training.examportal.service.ResultService;
import com.innovaturelabs.training.examportal.view.ResultListView;



@RestController
@RequestMapping("/result")
public class ResultController {
	
	
	@Autowired
	private ResultService resultService;
	
	
	 @GetMapping
	    public Collection<ResultListView> list(){
	    	return resultService.list();
	    }
	
   @GetMapping("/user")
   public Collection<ResultListView> get(){
	   return resultService.get();
	   
   }
}		