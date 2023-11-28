/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaturelabs.training.examportal.service.impl;

import com.innovaturelabs.training.examportal.entity.User;
import com.innovaturelabs.training.examportal.exception.BadRequestException;
import com.innovaturelabs.training.examportal.exception.NotFoundException;
import com.innovaturelabs.training.examportal.form.LoginForm;
import com.innovaturelabs.training.examportal.form.UserForm;
import com.innovaturelabs.training.examportal.repository.UserRepository;
import com.innovaturelabs.training.examportal.security.config.SecurityConfig;
import com.innovaturelabs.training.examportal.security.util.InvalidTokenException;
import com.innovaturelabs.training.examportal.security.util.SecurityUtil;
import com.innovaturelabs.training.examportal.security.util.TokenExpiredException;
import com.innovaturelabs.training.examportal.security.util.TokenGenerator;
import com.innovaturelabs.training.examportal.security.util.TokenGenerator.Status;
import com.innovaturelabs.training.examportal.security.util.TokenGenerator.Token;
import com.innovaturelabs.training.examportal.service.UserService;
import com.innovaturelabs.training.examportal.view.LoginView;
import com.innovaturelabs.training.examportal.view.UserView;

import net.bytebuddy.utility.RandomString;

import static com.innovaturelabs.training.examportal.security.AccessTokenUserDetailsService.PURPOSE_ACCESS_TOKEN;

import java.awt.desktop.UserSessionEvent.Reason;
import java.time.Duration;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

@Service
public class UserServiceImpl implements UserService {

	private static final String PURPOSE_REFRESH_TOKEN = "REFRESH_TOKEN";

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TokenGenerator tokenGenerator;

	@Autowired
	private SecurityConfig securityConfig;

	private TextEncryptor textEncryptor;

	@Autowired
	private EmailServiceImpl emailServiceImpl;

	@Override
	public UserView add(UserForm form) {
		return new UserView(userRepository.save(
				new User(form.getName(), form.getEmail(), passwordEncoder.encode(form.getPassword()), form.getRole())));
	}

	@Override
	public UserView currentUser() {
		return new UserView(
				userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(NotFoundException::new));
	}

	@Override
	public LoginView login(LoginForm form, Errors errors) throws BadRequestException {
		if (errors.hasErrors()) {
			throw badRequestException();
		}
		User user = userRepository.findByEmail(form.getEmail()).orElseThrow(UserServiceImpl::emailRequestException);
		if (!passwordEncoder.matches(form.getPassword(), user.getPassword())) {
			throw new BadRequestException("password incorrect");
		}

		String id = String.format("%010d", user.getUserId());
		Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
		Token refreshToken = tokenGenerator.create(PURPOSE_REFRESH_TOKEN, id + user.getPassword(),
				securityConfig.getRefreshTokenExpiry());
		return new LoginView(user, accessToken, refreshToken);
	}

	@Override
	public LoginView refresh(String refreshToken) throws BadRequestException {
		Status status;
		try {
			status = tokenGenerator.verify(PURPOSE_REFRESH_TOKEN, refreshToken);
		} catch (InvalidTokenException e) {
			throw new BadRequestException("Invalid token", e);
		} catch (TokenExpiredException e) {
			throw new BadRequestException("Token expired", e);
		}

		int userId;
		try {
			userId = Integer.parseInt(status.data.substring(0, 10));
		} catch (NumberFormatException e) {
			throw new BadRequestException("Invalid token", e);
		}

		String password = status.data.substring(10);

		User user = userRepository.findByUserIdAndPassword(userId, password)
				.orElseThrow(UserServiceImpl::badRequestException);

		String id = String.format("%010d", user.getUserId());
		Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
		return new LoginView(user, new LoginView.TokenView(accessToken.value, accessToken.expiry),
				new LoginView.TokenView(refreshToken, status.expiry));
	}

	private static BadRequestException badRequestException() {
		return new BadRequestException("Invalid credentials");
	}

	private static BadRequestException emailRequestException() {
		return new BadRequestException("Enter correct email");
	}

	@Override
	public Collection<User> list() {
		return userRepository.findAllByRole(User.Role.USER.value);
	}

	@Override
	@Transactional
	public UserView updateUserById(Integer userId, UserForm form) {
		Optional<User> otherUser = userRepository.findById(userId);
		return otherUser.map((user) -> {
			return new UserView(userRepository.save(user.update(form)));
		}).orElseThrow(NotFoundException::new);
	}

	@Override
	@Transactional
	public UserView updateUserById(UserForm form) {
		Optional<User> currentUser = userRepository.findById(SecurityUtil.getCurrentUserId());
		return currentUser.map((user) -> {
			return new UserView(userRepository.save(user.update(form)));
		}).orElseThrow(NotFoundException::new);

	}

	@Override
	@Transactional
	public void delete(Integer userId) throws NotFoundException {
		userRepository.delete(userRepository.findById(userId).orElseThrow(NotFoundException::new));

	}

	@Override
	public void generateToken(String email) {

		long time = System.currentTimeMillis();

		Duration passwordResetTokenExpiry = Duration.ofMinutes(10);

		String randomCode = RandomString.make(40);
		User user = userRepository.findByEmail(email).orElseThrow(NotFoundException::new);
		System.out.println("====" + user.getEmail());

		String token = randomCode + "#" + time + "#" + passwordResetTokenExpiry.toMillis();
		textEncryptor = Encryptors.text("7C481ADD4AF55AB8", "374195D5E3080DC1");

		String encryptedToken = textEncryptor.encrypt(token);

		user.setResetPasswordToken(encryptedToken);
		userRepository.save(user);
		String siteURL = "http://localhost:4200/reset-password/";
		String recipient = user.getEmail();
		String subject = "Reset Password";
		String body = "Hii " + user.getName()
				+ ",\n\n We have recieved a request to reset your password.\n\nUse the below link to to set up a new password for your account.This link will be expired in 10 minutes\n\n"
				+ siteURL + encryptedToken
				+ "\n\nIf this was a mistake ,just ignore this email and nothing will happen";
		emailServiceImpl.SentEmail(recipient, subject, body);

//        }

	}

	@Override
	public UserView resetPassword(String token, String newPassword) {
		System.out.println("===00000000======");
		User user = userRepository.findByResetPasswordToken(token).orElseThrow(NotFoundException::new);
		System.out.println("===11111111======");
		if (user != null) {
			String decryptedToken = textEncryptor.decrypt(token);

			System.out.println("======================" + decryptedToken);
			String[] arrofString = decryptedToken.split("#");

			long createdTime = Long.parseLong(arrofString[1]);
			long expiryTime = Long.parseLong(arrofString[2]);

			long currentTime = System.currentTimeMillis();

			long timeDiff = currentTime - createdTime;
			
			System.out.println("===passs==="+newPassword);
			if (timeDiff > expiryTime) {
				System.out.println("======token expired=====");
				user.setResetPasswordToken(null);
				throw new TokenExpiredException("TOKEN EXPIRED");
			} else {
				user.setPassword(passwordEncoder.encode(newPassword));
				user.setResetPasswordToken(null);

				userRepository.save(user);
//    userRepository.save(user);
				return new UserView(user);
			}
		} else {
			throw new NotFoundException();
		}

	}

}
