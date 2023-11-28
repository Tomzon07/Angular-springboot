package com.innovaturelabs.training.examportal.repository;


import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.innovaturelabs.training.examportal.entity.UserProfile;

public interface UserProfileRepository  extends  Repository<UserProfile ,Integer>{
	
	Optional<UserProfile> findById(Integer userId);
	
	Optional<UserProfile> findByUserUserId(Integer userId);
	
	UserProfile save(UserProfile userProfile);

	Optional<UserProfile>findByUserUserIdAndStatus(Integer userId,byte Status);
	
	void delete(UserProfile userProfile);

	

}