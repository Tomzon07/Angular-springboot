package com.innovaturelabs.training.examportal.service;




import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import com.innovaturelabs.training.examportal.exception.NotFoundException;
import com.innovaturelabs.training.examportal.form.UserProfileForm;
import com.innovaturelabs.training.examportal.view.UserProfileListView;

@Service

public interface UserProfileService {
	
	UserProfileListView add(UserProfileForm form);
	
	UserProfileListView get()throws NotFoundException;
	
	UserProfileListView update(UserProfileForm form)throws NotFoundException;
	
	void delete(Integer userId)throws NotFoundException;

	public HttpEntity<byte[]> getProfilePic(Integer userId);
	


	
}