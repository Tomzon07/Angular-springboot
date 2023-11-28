package com.innovaturelabs.training.examportal.service.impl;


import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import com.innovaturelabs.training.examportal.entity.User;
import com.innovaturelabs.training.examportal.entity.UserProfile;
import com.innovaturelabs.training.examportal.exception.NotFoundException;
import com.innovaturelabs.training.examportal.form.UserProfileForm;
import com.innovaturelabs.training.examportal.repository.UserProfileRepository;
import com.innovaturelabs.training.examportal.repository.UserRepository;
import com.innovaturelabs.training.examportal.security.util.SecurityUtil;
import com.innovaturelabs.training.examportal.service.UserProfileService;
import com.innovaturelabs.training.examportal.service.UserService;
import com.innovaturelabs.training.examportal.view.UserProfileListView;
import com.innovaturelabs.training.examportal.util.FileUtil;

@Configuration
@Service
public class UserProfileServiceImpl implements 	UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public UserProfileListView add(UserProfileForm form) {
		User user= userRepository.findById(SecurityUtil.getCurrentUserId()).get();
		if(user.getProfileStatus()==0) {
			user.setProfileStatus(User.Add.ADDED.value);
			userRepository.save(user);
		return new UserProfileListView(userProfileRepository.save(new UserProfile(form,SecurityUtil.getCurrentUserId())));
		}else {
			return this.get();
		}
	}


	@Override
	public UserProfileListView get() throws NotFoundException{
		
		System.out.println(SecurityUtil.getCurrentUserId());
		
		return new UserProfileListView(userProfileRepository.findByUserUserId(SecurityUtil.getCurrentUserId()).orElseThrow(NotFoundException::new));
		
	}
	@Override
    @Transactional
	public UserProfileListView update(UserProfileForm form)  {
	    Optional<UserProfile>currentUser=userProfileRepository.findByUserUserId(SecurityUtil.getCurrentUserId());
	    return currentUser.map((UserProfile)->{
	    	return new UserProfileListView(userProfileRepository.save(UserProfile.update(form)));
				}).orElseThrow(NotFoundException::new);
	}
	


	@Override
	@Transactional
	public void delete(Integer userId) throws NotFoundException {
		UserProfile userProfile=userProfileRepository.findByUserUserIdAndStatus(userId,UserProfile.Status.ACTIVE.value).orElseThrow(NotFoundException::new);
		userProfile.setStatus(UserProfile.Status.DELETED.value);
		userProfile.setUpdateDate(new Date());
		userProfileRepository.save(userProfile);
		System.out.println("inactive");
        return;		
		
	}
	
	@Override
    public HttpEntity<byte[]> getProfilePic(Integer userId) {

        String profilePic = userProfileRepository.findByUserUserId(SecurityUtil.getCurrentUserId())
                .orElseThrow(NotFoundException::new).getPhotos();

        byte[] file = FileUtil.getProfilePic(profilePic);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(file.length);

        return new HttpEntity<>(file, headers);

    }
	
	

}

