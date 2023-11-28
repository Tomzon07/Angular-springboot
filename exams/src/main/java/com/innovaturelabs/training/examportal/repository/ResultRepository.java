package com.innovaturelabs.training.examportal.repository;


import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.innovaturelabs.training.examportal.entity.Result;
import com.innovaturelabs.training.examportal.view.ResultListView;

public interface ResultRepository extends Repository<Result, Integer> {
	
	
   Collection<Result> findAllByUserUserId( Integer userId);
   
   Collection<Result> findAll();
    
	Result save(Result result);
    
}