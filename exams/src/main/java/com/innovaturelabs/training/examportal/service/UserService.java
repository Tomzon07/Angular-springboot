
package com.innovaturelabs.training.examportal.service;

import com.innovaturelabs.training.examportal.entity.User;
import com.innovaturelabs.training.examportal.exception.BadRequestException;
import com.innovaturelabs.training.examportal.exception.NotFoundException;
import com.innovaturelabs.training.examportal.form.LoginForm;
import com.innovaturelabs.training.examportal.form.UserForm;
import com.innovaturelabs.training.examportal.view.LoginView;
import com.innovaturelabs.training.examportal.view.UserView;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;



@Service
public interface UserService {

    UserView add(UserForm form);

    UserView currentUser();
    
    LoginView login(LoginForm form, Errors errors) throws BadRequestException;

    LoginView refresh(String refreshToken) throws BadRequestException;
    
    UserView updateUserById(Integer UserId,UserForm form) throws NotFoundException; 

    Collection<User> list();
    
    UserView updateUserById(UserForm form)throws NotFoundException;
    
    void delete(Integer userId)throws NotFoundException;

	void generateToken(String email);
	
	UserView resetPassword(String token,String newPassword);
	
	
    
}
