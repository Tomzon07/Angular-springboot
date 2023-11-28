package com.innovaturelabs.training.examportal.service;

import java.util.*;
import org.springframework.stereotype.*;
import com.innovaturelabs.training.examportal.view.ResultListView;


@Service
public interface ResultService {

	Collection<ResultListView>list();
	
	Collection<ResultListView> get();
	 

}
