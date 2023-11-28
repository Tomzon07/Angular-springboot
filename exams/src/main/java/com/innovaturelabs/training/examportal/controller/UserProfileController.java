package com.innovaturelabs.training.examportal.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.innovaturelabs.training.examportal.entity.UserProfile;
import com.innovaturelabs.training.examportal.exception.NotFoundException;
import com.innovaturelabs.training.examportal.form.UserProfileForm;
import com.innovaturelabs.training.examportal.repository.UserProfileRepository;
import com.innovaturelabs.training.examportal.security.util.SecurityUtil;
import com.innovaturelabs.training.examportal.service.UserProfileService;
import com.innovaturelabs.training.examportal.util.FileUtil;
import com.innovaturelabs.training.examportal.view.UserProfileListView;

@RestController
@RequestMapping("/userprofile")
public class UserProfileController {

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@PostMapping
	public UserProfileListView add(@Valid @RequestBody UserProfileForm form) {
		return userProfileService.add(form);
	}

	@GetMapping
	public UserProfileListView get() {
		return userProfileService.get();

	}

	@PutMapping("/update")
	public UserProfileListView update(@Valid @RequestBody UserProfileForm form) {
		return userProfileService.update(form);
	}

	@DeleteMapping
	public void delete() {
		userProfileService.delete(SecurityUtil.getCurrentUserId());
	}

	@PostMapping("/save")
	public void saveUserProfile(@RequestParam("image") MultipartFile multipartFile) throws IOException {

		UserProfile userProfile = userProfileRepository.findByUserUserId(SecurityUtil.getCurrentUserId())
				.orElseThrow(NotFoundException::new);

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

		userProfile.setPhotos(fileName);

		userProfileRepository.save(userProfile);

//		String UploadDir = "userPro	file-photos/" + savedUserProfile.getUserprofileId();

		FileUtil.saveUserProfile(fileName, multipartFile);

	}

	@GetMapping("/profile")
	public HttpEntity<byte[]> getProfilePic() {

		return userProfileService.getProfilePic(SecurityUtil.getCurrentUserId());

	}
}